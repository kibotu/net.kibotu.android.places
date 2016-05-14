package net.kibotu.berlinplaces.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.common.android.utils.ContextHelper;
import com.common.android.utils.logging.Logger;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.google.nearby.Nearby;
import net.kibotu.berlinplaces.models.google.nearby.Place;
import net.kibotu.berlinplaces.ui.BaseFragment;

import io.nlopez.smartlocation.SmartLocation;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.kibotu.berlinplaces.network.RequestProvider.getNearbyPlaces;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
@RuntimePermissions
public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;

    GoogleMap map;

    @Override
    public int getLayout() {
        return R.layout.fragment_map;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @NonNull
    @Override
    public String getTitle() {
        return "Places";
    }

    @NonNull
    @Override
    public String getScreenName() {
        return "Places";
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setBuildingsEnabled(true);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);

        LatLng defaultLocation = new LatLng(52.5219184, 13.4132147);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 17f));

        if (!isLocationServiceEnabled()) {
            onNoLocationServiceAvailable();
        }
    }

    void onLocationUpdated(Location location) {
        Logger.v(tag(), "onLocationUpdated " + location);

        LatLng newLocation = new LatLng(location.getLatitude(), location.getLongitude());
//        map.moveCamera(CameraUpdateFactory.newLatLng(newLocation));

        map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                .target(newLocation)
                // .target(map.getCameraPosition().target)
                .zoom(map.getCameraPosition().zoom)
                .bearing(location.getBearing())
                .tilt(45)
                .build()));


        getNearbyPlaces(location.getLatitude(), location.getLongitude(), 500, "club,night_club").enqueue(new Callback<Nearby>() {
            @Override
            public void onResponse(Call<Nearby> call, Response<Nearby> response) {

                Nearby nearby = response.body();

                map.clear();

                for (Place place : nearby.results) {

                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(place.geometry.location.lat, place.geometry.location.lng)));

//                    Picasso.with(getContext()).load(place.icon).into(new Target() {
//                        @Override
//                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                            map.addMarker(new MarkerOptions()
//                                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
//                                    .position(new LatLng(place.geometry.location.lat, place.geometry.location.lng)));
//                        }
//
//                        @Override
//                        public void onBitmapFailed(Drawable errorDrawable) {
//
//                        }
//
//                        @Override
//                        public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                        }
//                    });
                }

                Logger.v(tag(), nearby.toString());
            }

            @Override
            public void onFailure(Call<Nearby> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void onNoLocationServiceAvailable() {
        Logger.v(tag(), "onNoLocationServiceAvailable");
    }

    @Override
    public void onResume() {
        super.onResume();

        MapFragmentPermissionsDispatcher.showLocationWithCheck(this);
    }

    public static boolean isLocationServiceEnabled() {
        return SmartLocation
                .with(ContextHelper.getContext())
                .location()
                .state()
                .locationServicesEnabled();
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    void showLocation() {
        SmartLocation.with(getContext()).location().start(this::onLocationUpdated);
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    void showRationaleForLocation(PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setMessage("gimmeh location permission >:D")
                .setPositiveButton("Sure", (dialog, button) -> request.proceed())
                .setNegativeButton("Nope", (dialog, button) -> request.cancel())
                .show();
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    void showDeniedForLocation() {
        Logger.toast("location permission denied");
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    void showNeverAskForLocation() {
        Logger.toast("location permission never asked again");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MapFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}

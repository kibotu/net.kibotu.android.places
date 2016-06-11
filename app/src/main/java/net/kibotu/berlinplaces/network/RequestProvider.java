package net.kibotu.berlinplaces.network;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.common.android.utils.logging.Logger;
import com.facebook.GraphRequest;

import net.kibotu.berlinplaces.BuildConfig;
import net.kibotu.berlinplaces.LocalUser;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.facebook.friends.FriendsResponse;
import net.kibotu.berlinplaces.models.facebook.login.LoginResponse;
import net.kibotu.berlinplaces.models.fake.person.People;
import net.kibotu.berlinplaces.models.foursquare.venues.Venues;
import net.kibotu.berlinplaces.models.google.nearby.Nearby;
import net.kibotu.berlinplaces.models.paul.blob.BlobRequest;
import net.kibotu.berlinplaces.models.paul.blob.BlobResponse;
import net.kibotu.berlinplaces.models.paul.events.Events;
import net.kibotu.berlinplaces.models.paul.locations.Locations;
import net.kibotu.berlinplaces.models.paul.login.AuthenticationRequest;
import net.kibotu.berlinplaces.models.paul.login.AuthenticationResponse;
import net.kibotu.berlinplaces.network.fake.FakeService;
import net.kibotu.berlinplaces.network.services.FourSquareService;
import net.kibotu.berlinplaces.network.services.GoogleApiService;
import net.kibotu.berlinplaces.network.services.PaulService;

import hugo.weaving.DebugLog;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

import static android.text.TextUtils.isEmpty;
import static com.common.android.utils.ContextHelper.getContext;
import static com.common.android.utils.misc.GsonProvider.getGson;
import static java.text.MessageFormat.format;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class RequestProvider {

    private static final String TAG = RequestProvider.class.getSimpleName();

    private static String baseUrl;

    public static String userToken;

    // region fake data

    @NonNull
    private static FakeService createFakeService() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sprotte.eltanin.uberspace.de/")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FakeService.class);
    }

    public static Observable<People> getFakePeople() {
        return createFakeService().getPeople();
    }

    // endregion

    // region foresquare

    private static FourSquareService createFoursquareService() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.foursquare.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FourSquareService.class);
    }

    /**
     * https://api.foursquare.com/v2/venues/search?client_id=KXHUI3FE4IJ4QC3Q5UUYWY0CWJ1B01RTKGHACMUPE2PJVT4Y&client_secret=42NDOQX1EVTZSNB4F2OSPUBHLD2HGU34BKFS0NFQDE5OMXV4&v=%20&ll=40.7,-74%20&query=dance
     */
    @DebugLog
    public static Observable<Venues> getNearbyPlacesFoursquare(double latitude, double longitude, String query) {
        return createFoursquareService().getNearbyPlaces(
                getContext().getString(R.string.foursquare_client_id),
                getContext().getString(R.string.foursquare_client_secret),
                "20130815",
                format("{0},{1}", latitude, longitude),
                query
        );
    }

    // endregion

    // region google

    @NonNull
    private static GoogleApiService createGoogleService() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(GoogleApiService.class);
    }

    // endregion

    @DebugLog
    public static Observable<Nearby> getNearbyPlaces(double latitude, double longitude, int radius, String types) {
        return createGoogleService().getNearbyPlaces(format("{0},{1}", latitude, longitude), radius, types, getContext().getString(R.string.google_server_key));
    }

    // region paul service

    @NonNull
    private static PaulService createPaulService() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder();

                    if (!isEmpty(userToken))
                        requestBuilder.header("token", userToken);

                    requestBuilder.header("app_id", getContext().getString(R.string.paul_service_app_id));

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();

        baseUrl = "http://172.27.100.143:3000/events/";
        baseUrl = "http://www.sprotte.eltanin.uberspace.de/";
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(PaulService.class);
    }

    @NonNull
    public static Observable<Events> getEvents(double latitude, double longitude, int distance, int skip, int limit) {
        return createPaulService().getEvents(latitude, longitude, distance, skip, limit);
    }

    @NonNull
    public static Observable<Locations> getPlaces(double latitude, double longitude, int distance, int skip, int limit) {
        return createPaulService().getPlaces(latitude, longitude, distance, skip, limit);
    }

    @NonNull
    public static Observable<AuthenticationResponse> login(@NonNull final String credentials) {
        return createPaulService().login(new AuthenticationRequest().setCredentials(credentials));
    }

    @NonNull
    public static Observable<AuthenticationResponse> register(@NonNull final String credentials) {
        return createPaulService().register(new AuthenticationRequest().setCredentials(credentials));
    }

    @NonNull
    public static Observable<BlobResponse> loadBlob() {
        return createPaulService().loadBlob();
    }

    @NonNull
    public static Observable<BlobResponse> storeBlob(String blob) {
        return createPaulService().storeBlob(new BlobRequest().setBlob(blob));
    }

    public static synchronized void setUserToken(@Nullable final String uuid) {
        userToken = uuid;
    }

    @NonNull
    public static Observable<LoginResponse> getFacebookMe() {
        return Observable.create(
                new Observable.OnSubscribe<LoginResponse>() {
                    @Override
                    public void call(Subscriber<? super LoginResponse> sub) {
                        GraphRequest request = GraphRequest.newMeRequest(
                                LocalUser.facebookAccessToken,
                                (object, response) -> {
                                    Logger.v(TAG, "[newMeRequest] " + response);
                                    LoginResponse loginResponse = getGson().fromJson(response.getRawResponse(), LoginResponse.class);
                                    sub.onNext(loginResponse);
                                    sub.onCompleted();
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,link");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }
                }
        );
    }

    @NonNull
    public static Observable<FriendsResponse> getFacebookFriendsWhoUseTheApp() {
        return Observable.create(
                new Observable.OnSubscribe<FriendsResponse>() {
                    @Override
                    public void call(Subscriber<? super FriendsResponse> sub) {
                        final String graphPath = "me/friends";
                        Logger.v(TAG, "[getFacebookFriendsWhoUseTheApp] " + graphPath + " token: " + LocalUser.facebookAccessToken.getToken());
                        GraphRequest request = GraphRequest.newMyFriendsRequest(
                                LocalUser.facebookAccessToken, (objects, response) -> {
                                    Logger.v(TAG, "[getFacebookFriendsWhoUseTheApp]" + response);
                                    sub.onNext(getGson().fromJson(response.getRawResponse(), FriendsResponse.class));
                                    sub.onCompleted();
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "data,paging");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }
                }
        );
    }

    // endregion
}

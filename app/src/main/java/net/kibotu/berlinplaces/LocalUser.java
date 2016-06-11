package net.kibotu.berlinplaces;

import android.location.Location;

import com.common.android.utils.logging.Logger;
import com.facebook.AccessToken;
import com.orhanobut.hawk.Hawk;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static net.kibotu.berlinplaces.misc.SnackbarExtensions.showDangerSnack;
import static net.kibotu.berlinplaces.network.RequestProvider.loadBlob;
import static net.kibotu.berlinplaces.network.RequestProvider.storeBlob;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class LocalUser {

    public static final String TAG = LocalUser.class.getSimpleName();
    public static AccessToken facebookAccessToken;
    public static String facebookToken;
    public static Location location = createDummyLocation();

    private static Location createDummyLocation() {
        Location location = new Location("placeholder");
        location.setLatitude(52.5534854);
        location.setLatitude(13.3595626);
        return location;
    }

    public static void save() {
        storeBlob("Hallo welt.")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(blobResponse -> {
                    Logger.v(TAG, "[loadBlob] " + blobResponse);

                }, throwable -> showDangerSnack(throwable.getMessage()));
    }

    public static void load() {
        loadBlob().subscribe(blobResponse -> {
            Logger.v(TAG, "[loadBlob] " + blobResponse);

        }, throwable -> showDangerSnack(throwable.getMessage()));
    }

    public static void setToken(AccessToken accessToken) {
        LocalUser.facebookAccessToken = accessToken;
        facebookToken = accessToken.getToken();
    }

    public static void setFacebookId(String id) {
        Hawk.put("facebook_id", id);
    }

    public static String getFacebookId() {
        return Hawk.get("facebook_id");
    }
}

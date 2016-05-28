package net.kibotu.berlinplaces;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.common.android.utils.ContextHelper;
import com.common.android.utils.logging.Logger;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.squareup.leakcanary.LeakCanary;

import net.kibotu.android.deviceinfo.library.Device;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class PlacesApplication extends MultiDexApplication {

    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;
    private boolean ENCRYPTION_ENABLED = false;

    @Override
    public void onCreate() {
        MultiDex.install(getApplicationContext());
        super.onCreate();
        LeakCanary.install(this);

        ContextHelper.init(this);
        Logger.setLogLevel(BuildConfig.DEBUG
                ? Logger.Level.VERBOSE
                : Logger.Level.SILENT);

        activityLifecycleCallbacks = createActivityCallBacks();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);

        configureCalligraphy();
    }

    private void configureCalligraphy() {
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath(getString(R.string.josefin_light))
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        ContextHelper.init(null);
        ContextHelper.setContext(null);
        Device.setContext(null);
        super.onTerminate();
    }


    private Application.ActivityLifecycleCallbacks createActivityCallBacks() {
        return new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                Device.setContext(activity);

                // Secure shared preferences
                Hawk.init(activity)
                        .setEncryptionMethod((BuildConfig.DEBUG && !ENCRYPTION_ENABLED)
                                ? HawkBuilder.EncryptionMethod.NO_ENCRYPTION
                                : HawkBuilder.EncryptionMethod.MEDIUM)
                        .setStorage(HawkBuilder.newSharedPrefStorage(activity))
                        .setLogLevel(BuildConfig.DEBUG
                                ? LogLevel.FULL
                                : LogLevel.NONE)
                        .build();
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        };
    }
}
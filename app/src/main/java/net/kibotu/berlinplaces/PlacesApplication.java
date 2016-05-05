package net.kibotu.berlinplaces;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.common.android.utils.ContextHelper;
import com.common.android.utils.logging.Logger;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class PlacesApplication extends MultiDexApplication {

    final Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = createActivityCallBacks();
    private boolean ENCRYPTION_ENABLED = false;

    private Application.ActivityLifecycleCallbacks createActivityCallBacks() {
        return new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                setContext(activity);

                Logger.setLogLevel(BuildConfig.DEBUG
                        ? Logger.Level.VERBOSE
                        : Logger.Level.SILENT);

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
                setContext(activity);
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

    private void setContext(Activity activity) {
        if (activity instanceof AppCompatActivity)
            ContextHelper.setContext((FragmentActivity) activity);
    }

    @Override
    public void onCreate() {
        MultiDex.install(getApplicationContext());
        super.onCreate();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        super.onTerminate();
    }
}
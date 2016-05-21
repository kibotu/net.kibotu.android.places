package net.kibotu.berlinplaces;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.common.android.utils.ContextHelper;
import com.common.android.utils.extensions.FragmentExtensions;
import com.common.android.utils.interfaces.LogTag;
import com.common.android.utils.logging.Logger;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import net.kibotu.android.materialmenu.MaterialMenu;

import java.util.concurrent.atomic.AtomicBoolean;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.common.android.utils.extensions.DeviceExtensions.hideKeyboard;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class BaseActivity extends AppCompatActivity implements LogTag {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public final static AtomicBoolean isRunning = new AtomicBoolean(false);

    @Override
    public void onResume() {
        isRunning.set(true);
        super.onResume();
    }

    @CallSuper
    @Override
    public void onPause() {
        isRunning.set(false);
        super.onPause();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */

    private boolean checkPlayServices() {
        final GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Logger.i(tag(), "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @NonNull
    @Override
    public String tag() {
        return getClass().getSimpleName();
    }

    @CallSuper
    @Override
    public void onBackPressed() {
        hideKeyboard();

        if (getFragmentManager().getBackStackEntryCount() > 0 && !ContextHelper.getActivity().isFinishing()) {
            getFragmentManager().popBackStack();
            FragmentExtensions.printBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(final Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return MaterialMenu.onCreateOptionsMenu(this, menu);
    }
}

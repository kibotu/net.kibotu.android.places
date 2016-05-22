package net.kibotu.berlinplaces;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.utils.ContextHelper;
import com.common.android.utils.extensions.ToastExtensions;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;

import net.kibotu.berlinplaces.ui.drawer.DrawerManager;
import net.kibotu.berlinplaces.ui.places.PlacesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.common.android.utils.ContextHelper.getAppCompatActivity;
import static com.common.android.utils.extensions.FragmentExtensions.replaceByFading;

public class MainActivity extends BaseActivity {

    private DrawerManager drawerManager;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerManager = new DrawerManager();
        drawerManager.onCreate(savedInstanceState);

        // initial fragment
        replaceByFading(new PlacesFragment());
    }

    public static boolean contains(String tag) {
        final FragmentManager supportFragmentManager = getAppCompatActivity().getSupportFragmentManager();
        for (int i = 0; i < supportFragmentManager.getBackStackEntryCount(); ++i)
            if (supportFragmentManager.getBackStackEntryAt(i).getName().equalsIgnoreCase(tag))
                return true;

        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerManager.hasOpenDrawer()) {
            drawerManager.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawerManager.onDestroy();
    }
}


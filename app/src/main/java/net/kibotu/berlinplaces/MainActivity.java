package net.kibotu.berlinplaces;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.common.android.utils.ContextHelper;

import net.kibotu.android.materialmenu.FragmentTag;
import net.kibotu.android.materialmenu.MaterialMenu;
import net.kibotu.android.materialmenu.MenuItem;
import net.kibotu.android.materialmenu.SimpleMaterialToolbar;
import net.kibotu.berlinplaces.ui.BaseFragment;
import net.kibotu.berlinplaces.ui.drawerLeft.LeftDrawerFragment;
import net.kibotu.berlinplaces.ui.drawerRight.RightDrawerFragment;
import net.kibotu.berlinplaces.ui.map.MapFragment;
import net.kibotu.berlinplaces.ui.places.PlacesFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.common.android.utils.extensions.FragmentExtensions.addBySlidingHorizontally;
import static com.common.android.utils.extensions.FragmentExtensions.currentFragment;
import static com.common.android.utils.extensions.FragmentExtensions.replaceByFading;
import static com.common.android.utils.extensions.FragmentExtensions.replaceBySlidingHorizontally;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialMenu.with(this)
                .addMenuItem(createUserMenu())
                .setActionBar(new SimpleMaterialToolbar(this))
                .setLeftDrawerFragment(new LeftDrawerFragment())
                .setRightDrawerFragment(new RightDrawerFragment());

        // initial fragment
        replaceByFading(new PlacesFragment());
    }

    private Collection<MenuItem> createUserMenu() {
        final List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem()
                .setLabel(R.string.map)
                .setIcon(R.drawable.ic_map_black_18dp)
                .setAction(v -> addToBackStack(new MapFragment())));

        menuItems.add(new MenuItem()
                .setLabel(R.string.places)
                .setIcon(R.drawable.ic_place_black_18dp)
                .setAction(v -> replaceToBackStack(new PlacesFragment())));

        return menuItems;
    }

    private void addToBackStack(@NonNull final BaseFragment fragment) {

        MaterialMenu.closeDrawers();

        if (currentFragment().getClass().equals(fragment.getClass()))
            return;

//        if (!contains(fragment))
        addBySlidingHorizontally(fragment);
    }

    private void replaceToBackStack(@NonNull final BaseFragment fragment) {

        MaterialMenu.closeDrawers();

        if (currentFragment().getClass().equals(fragment.getClass()))
            return;

//        if (!contains(fragment))
        replaceBySlidingHorizontally(fragment);
    }

    public static boolean contains(FragmentTag tag) {
        final FragmentManager supportFragmentManager = ContextHelper.getAppCompatActivity().getSupportFragmentManager();
        for (int i = 0; i < supportFragmentManager.getBackStackEntryCount(); ++i)
            if (supportFragmentManager.getBackStackEntryAt(i).getName().equalsIgnoreCase(tag.fragmentTag()))
                return true;

        return false;
    }

    @Override
    public void onBackPressed() {
        if (MaterialMenu.hasOpenDrawer()) {
            MaterialMenu.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}


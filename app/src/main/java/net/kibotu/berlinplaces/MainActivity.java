package net.kibotu.berlinplaces;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.common.android.utils.ContextHelper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import net.kibotu.berlinplaces.ui.DrawerManagerProvider;
import net.kibotu.berlinplaces.ui.drawer.DrawerManager;
import net.kibotu.berlinplaces.ui.stackOptions.StackOptionFragment;

import java.util.ArrayList;

import butterknife.BindView;

import static com.common.android.utils.extensions.FragmentExtensions.replaceByFading;
import static com.common.android.utils.extensions.ResourceExtensions.color;
import static net.kibotu.berlinplaces.misc.Extensions.changeStatusBarColor;

public class MainActivity extends BaseActivity implements DrawerManagerProvider {

    @BindView(R.id.overlay_container)
    View overlayouContainer;

    private DrawerManager drawerManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerManager = new DrawerManager();
        drawerManager.onCreate(savedInstanceState);

        // test place stacks
//         replaceByFading(new PlacesStackFragment());

        // test places stagggered list
//        replaceByFading(new PlacesListFragment());

        // test place
//        getEvents()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(events -> {
//                    showPlace(events.events.get(0));
//                }, Throwable::printStackTrace);

        boarding();
    }

    private void boarding() {
        ArrayList<PaperOnboardingPage> dataForOnboarding = getDataForOnboarding();
        final PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(dataForOnboarding);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.overlay_container, onBoardingFragment)
                .commit();

        // set start color to first page
        changeStatusBarColor(dataForOnboarding.get(0).getBgColor());

        // change colors every swipe
        onBoardingFragment.setOnChangeListener((from, to)
                -> changeStatusBarColor(dataForOnboarding.get(to).getBgColor()));

        onBoardingFragment.setOnRightOutListener(() -> {

            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(onBoardingFragment)
                    .commit();

            changeStatusBarColor(color(R.color.colorPrimary));

//            replaceByFading(new LoginFragment());
//            replaceByFading(new PlacesStackFragment());
            replaceByFading(new StackOptionFragment());
        });
    }

    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        final ArrayList<PaperOnboardingPage> elements = new ArrayList<>();

        elements.add(new PaperOnboardingPage("Map", "Nearby map.",
                Color.parseColor("#678FB4"),
                R.drawable.ic_map_black_18dp,
                R.drawable.ic_map_black_18dp));

        elements.add(new PaperOnboardingPage("Places", "We have them.",
                Color.parseColor("#65B0B4"),
                R.drawable.ic_place_black_18dp,
                R.drawable.ic_place_black_18dp));

        return elements;
    }

    public static boolean contains(String tag) {
        final FragmentManager supportFragmentManager = ContextHelper.getFragmentActivity().getSupportFragmentManager();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        drawerManager.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @NonNull
    @Override
    public DrawerManager getDrawerManager() {
        return drawerManager;
    }
}


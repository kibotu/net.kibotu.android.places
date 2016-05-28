package net.kibotu.berlinplaces;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.common.android.utils.ContextHelper;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import net.kibotu.berlinplaces.ui.drawer.DrawerManager;
import net.kibotu.berlinplaces.ui.places.PlacesFragment;

import java.util.ArrayList;

import static com.common.android.utils.extensions.FragmentExtensions.replaceByFading;

public class MainActivity extends BaseActivity {

    private DrawerManager drawerManager;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerManager = new DrawerManager();
        drawerManager.onCreate(savedInstanceState);

//        boarding();

        // test place stacks
        // replaceByFading(new PlacesStackFragment());

        // test places stagggered list
         replaceByFading(new PlacesFragment());

        // test place
//        getEvents()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(events -> {
//                    showPlace(events.events.get(0));
//                }, Throwable::printStackTrace);
    }

    private void boarding() {
        final PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(getDataForOnboarding());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, onBoardingFragment)
                .commit();

        onBoardingFragment.setOnRightOutListener(() -> {
            replaceByFading(new PlacesFragment());
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
}


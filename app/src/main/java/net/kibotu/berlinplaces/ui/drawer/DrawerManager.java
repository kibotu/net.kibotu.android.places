package net.kibotu.berlinplaces.ui.drawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.utils.interfaces.LogTag;
import com.common.android.utils.logging.Logger;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.ui.map.MapFragment;
import net.kibotu.berlinplaces.ui.places.PlacesFragment;
import net.kibotu.berlinplaces.ui.places.PlacesStackFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.common.android.utils.ContextHelper.getActivity;
import static com.common.android.utils.ContextHelper.getAppCompatActivity;
import static com.common.android.utils.ContextHelper.getContext;
import static com.common.android.utils.extensions.FragmentExtensions.*;

/**
 * Created by Nyaruhodo on 22.05.2016.
 */

public class DrawerManager implements LogTag{

    @NonNull
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;

    private CrossfadeDrawerLayout crossfadeDrawerLayout;
    private Drawer leftDrawer;
    private Drawer rightDrawer;

    public DrawerManager() {
        unbinder = ButterKnife.bind(getActivity());
    }

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        initCrossfadeDrawer(savedInstanceState);
        initRightDrawer(savedInstanceState);

        leftDrawer.setToolbar(getActivity(), toolbar, true);
    }

    private void initRightDrawer(@Nullable final Bundle savedInstanceState) {
        rightDrawer = new DrawerBuilder()
                .withActivity(getActivity())
                .withSavedInstance(savedInstanceState)
                .withDrawerGravity(Gravity.END)
                .build();
    }

    private void initCrossfadeDrawer(@Nullable final Bundle savedInstanceState) {
        getAppCompatActivity().setSupportActionBar(toolbar);

        leftDrawer = new DrawerBuilder()
                .withActivity(getActivity())
                .withDrawerLayout(R.layout.crossfade_drawer)
                .withDrawerWidthDp(72)
                .withGenerateMiniDrawer(true)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Map").withIcon(GoogleMaterial.Icon.gmd_map).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Places List").withIcon(GoogleMaterial.Icon.gmd_place).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Places Stack").withIcon(GoogleMaterial.Icon.gmd_place).withIdentifier(3)
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    Logger.v(tag(), "position" + position);

                    switch (position)
                    {
                        case 0:
                            replaceByFading(new MapFragment());
                            return true;
                        case 1:
                            replaceByFading(new PlacesFragment());
                            return true;
                        case 2:
                            replaceByFading(new PlacesStackFragment());
                            return true;
                    }

                    return false;
                })
                .build();

        crossfadeDrawerLayout = (CrossfadeDrawerLayout) leftDrawer.getDrawerLayout();

        //define maxDrawerWidth
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(getContext()));
        //add second view (which is the miniDrawer)
        final MiniDrawer miniResult = leftDrawer.getMiniDrawer();
        //build the view for the MiniDrawer
        View view = miniResult.build(getContext());
        //set the background of the MiniDrawer as this would be transparent
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(getContext(), com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.material_drawer_background));
        //we do not have the MiniDrawer view during CrossfadeDrawerLayout creation so we will add it here
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    leftDrawer.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });
    }

    public void onDestroy() {
        unbinder.unbind();
    }

    public boolean hasOpenDrawer() {
        return leftDrawer.isDrawerOpen() || rightDrawer.isDrawerOpen();
    }

    public void closeDrawers() {
        rightDrawer.closeDrawer();
        leftDrawer.closeDrawer();
    }

    @NonNull
    @Override
    public String tag() {
        return getClass().getSimpleName();
    }
}

package net.kibotu.berlinplaces.ui.drawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.common.android.utils.ContextHelper.getActivity;
import static com.common.android.utils.ContextHelper.getAppCompatActivity;
import static com.common.android.utils.ContextHelper.getContext;

/**
 * Created by Nyaruhodo on 22.05.2016.
 */

public class DrawerManager {

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
    }

    private void initRightDrawer(@Nullable final Bundle savedInstanceState) {
        rightDrawer = new DrawerBuilder()
                .withActivity(getActivity())
                .withToolbar(toolbar)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("hallo3").withIcon(GoogleMaterial.Icon.gmd_access_time).withIdentifier(2)
                )
                .withDrawerGravity(Gravity.END)
                .build();
    }

    private void initCrossfadeDrawer(@Nullable final Bundle savedInstanceState) {
        getAppCompatActivity().setSupportActionBar(toolbar);

        leftDrawer = new DrawerBuilder()
                .withActivity(getActivity())
                .withActionBarDrawerToggle(true)
                .withDrawerLayout(R.layout.crossfade_drawer)
                .withDrawerWidthDp(72)
                .withGenerateMiniDrawer(true)
                .withToolbar(toolbar)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("hallo").withIcon(GoogleMaterial.Icon.gmd_access_time).withIdentifier(1)
                )
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
}

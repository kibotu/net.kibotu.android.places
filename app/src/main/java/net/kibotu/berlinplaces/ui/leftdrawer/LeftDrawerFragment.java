package net.kibotu.berlinplaces.ui.leftdrawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.ui.BaseFragment;

import butterknife.BindView;

import static com.common.android.utils.extensions.ViewExtensions.setLayoutMargin;
import static net.kibotu.android.deviceinfo.library.display.Display.getStatusBarHeight;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class LeftDrawerFragment extends BaseFragment {

    @NonNull
    @BindView(R.id.left_drawer_layout)
    View layout;
    @NonNull
    @BindView(R.id.drawer_title)
    TextView title;
    @NonNull
    @BindView(R.id.main_menu_list)
    RecyclerView list;

    @Override
    public int getLayout() {
        return R.layout.left_drawer;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        setLayoutMargin(layout, 0, getStatusBarHeight(), 0, 0);

        title.setText("Left Drawer Title");

        list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @NonNull
    @Override
    public String getTitle() {
        return tag();
    }

    @NonNull
    @Override
    public String getScreenName() {
        return tag();
    }
}
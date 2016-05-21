package net.kibotu.berlinplaces.ui.drawerRight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.ui.BaseFragment;

import butterknife.BindView;

import static net.kibotu.android.materialmenu.ViewExtensions.getStatusBarHeight;
import static net.kibotu.android.materialmenu.ViewExtensions.setLayoutMargin;

/**
 * Created by Nyaruhodo on 21.05.2016.
 */
public class RightDrawerFragment extends BaseFragment {

    @NonNull
    @BindView(R.id.right_drawer_layout)
    View layout;
    @NonNull
    @BindView(R.id.title)
    TextView title;

    @Override
    public int getLayout() {
        return R.layout.right_drawer;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        setLayoutMargin(layout, 0, getStatusBarHeight(), 0, 0);

        title.setText("Right Drawer Title");
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

package net.kibotu.berlinplaces.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.utils.ContextHelper;
import com.common.android.utils.interfaces.DispatchTouchEvent;
import com.common.android.utils.interfaces.LayoutProvider;
import com.common.android.utils.interfaces.LogTag;
import com.common.android.utils.interfaces.TitleProvider;

import net.kibotu.android.bloodhound.ScreenNameProvider;
import net.kibotu.berlinplaces.ui.drawer.DrawerManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.common.android.utils.extensions.ViewExtensions.getContentRoot;
import static com.common.android.utils.extensions.ViewExtensions.hideOnLostFocus;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public abstract class BaseFragment extends Fragment implements LogTag, TitleProvider, DispatchTouchEvent, ScreenNameProvider, LayoutProvider {

    protected View rootView;

    private Unbinder unbinder;

    public BaseFragment() {
    }

    @NonNull
    @Override
    final public String tag() {
        return getClass().getSimpleName();
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        onViewCreated(savedInstanceState);
        updateMenu();
    }

    public void updateMenu() {

        if (getActivity() instanceof DrawerManagerProvider) {
            final DrawerManager drawerManager = ((DrawerManagerProvider) getContext()).getDrawerManager();
            drawerManager.setTitle(getTitle());
            drawerManager.setExpanded(isExpanded(), expandingIsAnimated());
        }
    }

    protected boolean expandingIsAnimated() {
        return false;
    }

    protected boolean isExpanded() {
        return false;
    }

    protected abstract void onViewCreated(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), container, false);
        }

        return rootView;
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull final MotionEvent event) {
        hideOnLostFocus(event, getViewsThatHideKeyboardOnFocusLoss());
        getContentRoot().requestFocus();
        return false;
    }

    @Nullable
    protected View[] getViewsThatHideKeyboardOnFocusLoss() {
        return null;
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

    @CallSuper
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public Context getContext() {
        return ContextHelper.getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
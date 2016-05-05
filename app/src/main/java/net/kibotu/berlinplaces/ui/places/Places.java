package net.kibotu.berlinplaces.ui.places;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.ui.BaseFragment;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class Places extends BaseFragment {

    @Override
    public int getLayout() {
        return R.layout.fragment_places;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    public String getTitle() {
        return tag();
    }

    @NonNull
    @Override
    public String getScreenName(@NonNull Context context) {
        return  tag();
    }
}

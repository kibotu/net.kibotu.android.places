package net.kibotu.berlinplaces.ui.stackOptions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;

import net.kibotu.berlinplaces.LocalUser;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.ui.BaseFragment;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

import static net.kibotu.berlinplaces.FragmentProvider.showPlacesStack;

/**
 * Created by Nyaruhodo on 13.06.2016.
 */

public class StackOptionFragment extends BaseFragment {

    @BindView(R.id.location)
    TextInputEditText location;

    @BindView(R.id.distance)
    TextInputEditText distance;

    @BindView(R.id.start_date)
    TextInputEditText startDate;

    @BindView(R.id.time_offset_max)
    TextInputEditText timeOffset;

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_stack_options;
    }

    @OnClick(R.id.default_button)
    void onDefaultPressed() {
        showPlacesStack(new StackOptions()
                .setLatitude(LocalUser.location.getLatitude())
                .setLongitute(LocalUser.location.getLongitude())
                .setRadius(1000)
                .setStartDateTime(new Date().getTime())
                .setTimeOffset(60 * 60 * 2));
    }

    @OnClick(R.id.submit)
    void onSubmitPressed() {

    }

    @NonNull
    @Override
    public String getTitle() {
        return "Options";
    }
}

package net.kibotu.berlinplaces.ui.place;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.utils.ContextHelper;
import com.squareup.picasso.Picasso;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.events.Event;
import net.kibotu.berlinplaces.ui.BaseFragment;

import org.parceler.Parcels;

import butterknife.BindView;

/**
 * Created by Nyaruhodo on 07.05.2016.
 */
public class PlaceFragment extends BaseFragment {

    @NonNull
    @BindView(R.id.photo)
    ImageView photo;

    @NonNull
    @BindView(R.id.label)
    TextView label;

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        final Bundle arguments = getArguments();
        if (arguments == null)
            return;

        final Event item = Parcels.unwrap(arguments.getParcelable(Event.class.getSimpleName()));

        label.setText(item.picture);
        Picasso.with(ContextHelper.getContext()).load(item.picture).into(photo);
    }

    @NonNull
    @Override
    public String getTitle() {
        return tag();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_place;
    }

    @NonNull
    @Override
    public String getScreenName() {
        return tag();
    }
}

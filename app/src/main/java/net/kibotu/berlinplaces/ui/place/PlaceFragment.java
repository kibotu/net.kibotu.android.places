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

import static com.common.android.utils.extensions.ViewExtensions.getContentRoot;

/**
 * Created by Nyaruhodo on 07.05.2016.
 */
public class PlaceFragment extends BaseFragment {

    ImageView photo;

    @NonNull
    @BindView(R.id.label)
    TextView label;

    @Override
    public int getLayout() {
        return R.layout.fragment_place;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        final Bundle arguments = getArguments();
        if (arguments == null)
            return;

        // get parallax image holder
        photo = (ImageView) getContentRoot().findViewById(R.id.main_backdrop);

        final Event item = Parcels.unwrap(arguments.getParcelable(Event.class.getSimpleName()));

        label.setText(item.picture);
        Picasso.with(ContextHelper.getContext()).load(item.picture).into(photo);
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

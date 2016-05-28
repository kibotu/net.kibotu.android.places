package net.kibotu.berlinplaces.ui.place;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.android.utils.ContextHelper;

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

    ImageView cover;

    @NonNull
    @BindView(R.id.label)
    TextView label;

    @NonNull
    @BindView(R.id.description)
    TextView description;

    private Event item;

    @Override
    public int getLayout() {
        return R.layout.fragment_place;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        final Bundle arguments = getArguments();
        if (arguments == null)
            return;

        item = Parcels.unwrap(arguments.getParcelable(Event.class.getSimpleName()));

        // get parallax image holder
        cover = (ImageView) getContentRoot().findViewById(R.id.main_backdrop);

        label.setText(item.name);
        description.setText(item.description);
        Glide.with(ContextHelper.getContext()).load(item.picture).into(cover);
    }

    @Override
    protected boolean isExpanded() {
        return true;
    }

    @Override
    protected boolean expandingIsAnimated() {
        return true;
    }

    @NonNull
    @Override
    public String getTitle() {
        return item.location.get(0).name;
    }

    @NonNull
    @Override
    public String getScreenName() {
        return tag();
    }
}

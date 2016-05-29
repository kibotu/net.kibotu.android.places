package net.kibotu.berlinplaces.ui.place;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.android.utils.ContextHelper;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.events.Event;
import net.kibotu.berlinplaces.ui.BaseFragment;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.common.android.utils.extensions.ViewExtensions.getContentRoot;
import static net.kibotu.berlinplaces.network.RequestProvider.getFakePeople;

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

    @NonNull
    @BindView(R.id.no_reply_count)
    TextView noReplyCount;
    @NonNull
    @BindView(R.id.maybe_count)
    TextView maybeCount;
    @NonNull
    @BindView(R.id.declined_count)
    TextView declinedCount;
    @NonNull
    @BindView(R.id.attending_count)
    TextView attendingCount;

    @NonNull
    @BindView(R.id.postal_code)
    TextView postalCode;
    @NonNull
    @BindView(R.id.country)
    TextView country;
    @NonNull
    @BindView(R.id.city)
    TextView city;
    @NonNull
    @BindView(R.id.street)
    TextView street;
    @NonNull
    @BindView(R.id.coordinates)
    TextView coordinates;

    @NonNull
    @BindView(R.id.tags)
    TextView tags;

    @NonNull
    @BindView(R.id.start_time)
    TextView startTime;

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

        noReplyCount.setText("No Replies: " + item.stats.noreplyCount);
        maybeCount.setText("Maybies: " + item.stats.maybeCount);
        declinedCount.setText("Declined: " + item.stats.declinedCount);
        attendingCount.setText("Attending: " + item.stats.attendingCount);

        postalCode.setText(item.location.get(0).address.zip);
        country.setText(item.location.get(0).address.country);
        city.setText(item.location.get(0).address.city);
        street.setText(item.location.get(0).address.street);
        coordinates.setText(item.location.get(0).address.latlng.get(0) + " " + item.location.get(0).address.latlng.get(1));

        final List<String> cats = new ArrayList<>();
        cats.addAll(item.location.get(0).types.yelpCategories);
        cats.addAll(item.location.get(0).types.googleCategories);
        cats.addAll(item.location.get(0).types.foursquarCategories);
        cats.addAll(item.location.get(0).types.facebookSubcategories);

        tags.setText(TextUtils.join(", ", cats));

        startTime.setText("" + item.startTime + " " + new Date(new Date().getTime() - item.startTime));

        downloadFakePeople();
    }

    private void downloadFakePeople() {
        getFakePeople()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(people -> {

                }, Throwable::printStackTrace);
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

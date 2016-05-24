package net.kibotu.berlinplaces.ui.places;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.common.android.utils.ContextHelper;
import com.common.android.utils.interfaces.LogTag;
import com.common.android.utils.logging.Logger;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.locations.Location;
import net.kibotu.berlinplaces.ui.BaseFragment;

import java.util.List;

import butterknife.BindView;
import link.fls.swipestack.SwipeStack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static net.kibotu.berlinplaces.network.RequestProvider.getLocations;

/**
 * Created by Nyaruhodo on 22.05.2016.
 */

public class PlacesStackFragment extends BaseFragment {

    @BindView(R.id.swipeStack)
    SwipeStack swipeStack;

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        Logger.v(tag(), "onViewCreated ");

        downloadNearby();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_places_stack;
    }

    private void downloadNearby() {
        getLocations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locations -> {
                    SwipeStackAdapter adapter = new SwipeStackAdapter(locations.locations);
                    swipeStack.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                    Logger.v(tag(), "downloadNearby " + locations.locations);

                }, Throwable::printStackTrace);
    }

    public static class SwipeStackAdapter extends BaseAdapter implements LogTag {

        private List<Location> mData;

        public SwipeStackAdapter(List<Location> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Location getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            convertView = ContextHelper.getActivity().getLayoutInflater().inflate(R.layout.card, parent, false);
            final Location location = mData.get(position);

            String facebookCover = location.images.facebookCover;
            Logger.v(tag(), "[image] " + facebookCover);

            final TextView name = (TextView) convertView.findViewById(R.id.name);
            name.setText(location.name);

            final ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);

            Glide.with(ContextHelper.getActivity())
                    .load(facebookCover)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

            convertView
                    .findViewById(R.id.card_layout)
                    .setBackground(new ColorDrawable(Color.argb(255, getRandomColor(), getRandomColor(), getRandomColor())));

            return convertView;
        }

        private static int getRandomColor() {
            return (int) (Math.random() * 75) + 255 - 75;
        }

        @NonNull
        @Override
        public String tag() {
            return getClass().getSimpleName();
        }
    }
}

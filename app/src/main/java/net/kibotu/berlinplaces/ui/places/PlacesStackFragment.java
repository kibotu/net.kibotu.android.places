package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.ImageVideoBitmapDecoder;
import com.common.android.utils.ContextHelper;
import com.common.android.utils.logging.Logger;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.facebook.events.Event;
import net.kibotu.berlinplaces.models.facebook.events.Events;
import net.kibotu.berlinplaces.ui.BaseFragment;

import java.util.List;

import butterknife.BindView;
import link.fls.swipestack.SwipeStack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static net.kibotu.berlinplaces.network.RequestProvider.getNearbyEvents;

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
        getNearbyEvents(52.520645, 13.409779, 1000, "venue")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                    SwipeStackAdapter adapter = new SwipeStackAdapter(events.events);
                    swipeStack.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    Logger.v(tag(), "downloadNearby " + events);

                }, Throwable::printStackTrace);
    }

    public static class SwipeStackAdapter extends BaseAdapter {

        private List<Event> mData;

        public SwipeStackAdapter(List<Event> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Event getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = ContextHelper.getActivity().getLayoutInflater().inflate(R.layout.card, parent, false);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);
            Event event = mData.get(position);
//            textViewCard.setText(event.eventName);

            Glide.with(ContextHelper.getActivity()).load(event.eventCoverPicture).into(imageView);

            return convertView;
        }
    }
}

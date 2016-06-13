package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.BaseAdapter;

import com.common.android.utils.logging.Logger;
import com.dtx12.android_animations_actions.actions.Interpolations;

import net.kibotu.berlinplaces.LocalUser;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.events.Event;
import net.kibotu.berlinplaces.models.paul.events.Events;
import net.kibotu.berlinplaces.network.RequestProvider;
import net.kibotu.berlinplaces.ui.BaseFragment;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import link.fls.swipestack.SwipeStack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.dtx12.android_animations_actions.actions.Actions.play;
import static com.dtx12.android_animations_actions.actions.Actions.run;
import static com.dtx12.android_animations_actions.actions.Actions.scaleTo;
import static com.dtx12.android_animations_actions.actions.Actions.sequence;
import static net.kibotu.berlinplaces.FragmentProvider.showPlace;
import static net.kibotu.berlinplaces.FragmentProvider.showPlacesList;
import static net.kibotu.berlinplaces.ui.ViewHelper.showError;

/**
 * Created by Nyaruhodo on 22.05.2016.
 */

public class PlacesStackFragment extends BaseFragment {

    @BindView(R.id.swipeStack)
    SwipeStack swipeStack;

    @BindView(R.id.undo)
    View undo;
    @BindView(R.id.reject)
    View reject;
    @BindView(R.id.like)
    View like;
    @BindView(R.id.favorite)
    View favorite;

    BaseAdapter adapter;

    @NonNull
    private final AtomicBoolean isAnimating = new AtomicBoolean(false);

    @Override
    public int getLayout() {
        return R.layout.fragment_places_stack;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        downloadEvents();

        undo.setOnClickListener(v -> {

            if (isAnimating.get())
                return;

            isAnimating.set(true);

            playZoomAnimation(v);

            swipeStack.resetStack();
        });

        reject.setOnClickListener(v -> {

            if (isAnimating.get())
                return;

            isAnimating.set(true);

            playZoomAnimation(v);
            swipeStack.swipeTopViewToLeft();
        });

        like.setOnClickListener(v -> {

            if (isAnimating.get())
                return;

            isAnimating.set(true);

            playZoomAnimation(v);
            swipeStack.swipeTopViewToRight();
        });

        favorite.setOnClickListener(v -> {

            if (isAnimating.get())
                return;

            isAnimating.set(true);

            playZoomAnimation(v);
            swipeStack.swipeTopViewToRight();
        });

        swipeStack.setSwipeProgressListener(new SwipeStack.SwipeProgressListener() {

            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeProgress(int position, float progress) {

//                if(adapter instanceof SwipeStackAdapterEvents)
//                    ((SwipeStackAdapterEvents) adapter).viewHolders.get(position).stamp
            }

            @Override
            public void onSwipeEnd(int position) {
            }
        });

        swipeStack.setListener(new SwipeStack.SwipeStackListener() {

            final Events events = new Events();

            @Override
            public void onViewSwipedToLeft(int position) {
                Logger.v(tag(), "[onViewSwipedToLeft] " + position);
            }

            @Override
            public void onViewSwipedToRight(int position) {
                Logger.v(tag(), "[onViewSwipedToRight] " + position);

                final Object item = adapter.getItem(position);
                if (item instanceof Event)
                    events.events.add((Event) item);
            }

            @Override
            public void onStackEmpty() {
                Logger.v(tag(), "[onStackEmpty] " + events.events.size());

                showPlacesList(events);
            }
        });
    }

    private void playZoomAnimation(@NonNull final View v) {
        play(sequence(
                scaleTo(1.75f, 1.75f, 0.25f, Interpolations.BackEaseIn),
                scaleTo(1f, 1f, 0.15f, Interpolations.BackEaseOut),
                run(() -> isAnimating.set(false))),
                v);
    }

    private void downloadPlaces() {
        RequestProvider.getPlaces(LocalUser.location.getLatitude(), LocalUser.location.getLongitude(), 1000, 0, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locations -> {
                    adapter = new SwipeStackAdapterPlaces(locations.locations);
                    swipeStack.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                    Logger.v(tag(), "downloadNearby " + locations.locations);

                }, showError());
    }

    private void downloadEvents() {
        RequestProvider.getEvents(LocalUser.location.getLatitude(), LocalUser.location.getLongitude(), 1000, 0, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                    adapter = new SwipeStackAdapterEvents(events.events)
                            .setOnItemClickListener((item, rowView, position) -> showPlace(item));
                    swipeStack.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                    Logger.v(tag(), "downloadNearby " + events.events);

                }, showError());
    }

    @Override
    protected boolean showToolbar() {
        return false;
    }

    @NonNull
    @Override
    public String getTitle() {
        return "Places & Events";
    }
}

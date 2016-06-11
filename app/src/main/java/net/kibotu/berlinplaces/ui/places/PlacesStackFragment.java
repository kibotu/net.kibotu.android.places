package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.common.android.utils.logging.Logger;
import com.dtx12.android_animations_actions.actions.Interpolations;

import net.kibotu.berlinplaces.LocalUser;
import net.kibotu.berlinplaces.R;
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

    @NonNull
    private final AtomicBoolean isAnimating = new AtomicBoolean(false);

    @Override
    public int getLayout() {
        return R.layout.fragment_places_stack;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        downloadNearby();

        undo.setOnClickListener(v -> {

            if (isAnimating.get())
                return;

            isAnimating.set(true);

            playZoomAnimation(v);
            swipeStack.swipeTopViewToLeft();
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

        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                Logger.v(tag(), "[onViewSwipedToLeft] " + position);
            }

            @Override
            public void onViewSwipedToRight(int position) {
                Logger.v(tag(), "[onViewSwipedToRight] " + position);
            }

            @Override
            public void onStackEmpty() {
                Logger.v(tag(), "[onStackEmpty]");
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

    private void downloadNearby() {
        RequestProvider.getPlaces(LocalUser.location.getLatitude(), LocalUser.location.getLongitude(), 1000, 0, 100)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locations -> {
                    SwipeStackAdapter adapter = new SwipeStackAdapter(locations.locations);
                    swipeStack.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                    Logger.v(tag(), "downloadNearby " + locations.locations);

                }, Throwable::printStackTrace);
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

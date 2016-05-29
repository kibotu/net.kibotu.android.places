package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.common.android.utils.logging.Logger;
import com.dtx12.android_animations_actions.actions.Interpolations;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.ui.BaseFragment;

import butterknife.BindView;
import link.fls.swipestack.SwipeStack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.dtx12.android_animations_actions.actions.Actions.play;
import static com.dtx12.android_animations_actions.actions.Actions.scaleTo;
import static com.dtx12.android_animations_actions.actions.Actions.sequence;
import static net.kibotu.berlinplaces.network.RequestProvider.getLocations;

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

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        downloadNearby();

        undo.setOnClickListener(createZoomAnimationClickListener());
        reject.setOnClickListener(createZoomAnimationClickListener());
        like.setOnClickListener(createZoomAnimationClickListener());
        favorite.setOnClickListener(createZoomAnimationClickListener());
    }

    @NonNull
    private View.OnClickListener createZoomAnimationClickListener() {
        return v -> play(sequence(scaleTo(1.75f, 1.75f, 0.25f, Interpolations.BackEaseIn), scaleTo(1f, 1f, 0.15f, Interpolations.BackEaseOut)), v);
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

package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;

import com.common.android.utils.logging.Logger;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.ui.BaseFragment;

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

}

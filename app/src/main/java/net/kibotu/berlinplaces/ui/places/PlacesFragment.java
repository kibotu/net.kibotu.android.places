package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import net.kibotu.android.recyclerviewpresenter.PresenterAdapter;
import net.kibotu.berlinplaces.FragmentProvider;
import net.kibotu.berlinplaces.LocalUser;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.events.Event;
import net.kibotu.berlinplaces.models.paul.events.Events;
import net.kibotu.berlinplaces.network.RequestProvider;
import net.kibotu.berlinplaces.ui.BaseFragment;

import org.parceler.Parcels;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static net.kibotu.berlinplaces.misc.SnackbarExtensions.showInfoSnack;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class PlacesFragment extends BaseFragment {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    PresenterAdapter<Event> adapter;

    @Nullable
    private Events cachedEvents;

    @Override
    public int getLayout() {
        return R.layout.fragment_places;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        restore(savedInstanceState);

        adapter = new PresenterAdapter<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((item, view, position) -> FragmentProvider.showPlace(item));

        addEvents(cachedEvents);

        swipeRefreshLayout.setOnRefreshListener(this::downloadNearby);

        downloadNearby();

        showInfoSnack("welcome");
    }

    private void restore(@Nullable final Bundle savedInstanceState) {
        if (savedInstanceState == null)
            return;

        cachedEvents = Parcels.unwrap(savedInstanceState.getParcelable(Events.class.getSimpleName()));
    }

    @Override
    public void onSaveInstanceState(@Nullable final Bundle outState) {
        super.onSaveInstanceState(outState);

        if (outState == null)
            return;

        outState.putParcelable(Events.class.getSimpleName(), Parcels.wrap(cachedEvents));
    }

    private void downloadNearby() {
        RequestProvider.getEvents(LocalUser.location.getLatitude(), LocalUser.location.getLongitude(), 1000, 0, 100)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {

                    cachedEvents = events;
                    addEvents(events);

                    swipeRefreshLayout.setRefreshing(false);
                }, Throwable::printStackTrace);
    }

    private void addEvents(@Nullable final Events events) {
        if (events == null)
            return;

        for (int i = 0; i < events.events.size(); ++i)
            adapter.add(events.events.get(i), PlacePresenter.class);

        adapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public String getTitle() {
        return "Places & Events";
    }

    @NonNull
    @Override
    public String getScreenName() {
        return tag();
    }
}

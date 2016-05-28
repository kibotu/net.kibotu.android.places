package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import net.kibotu.android.recyclerviewpresenter.PresenterAdapter;
import net.kibotu.berlinplaces.FragmentProvider;
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
//        ToastExtensions.showSuccessToast("welcome");

//        RequestProvider.getMockedFacebookEvents().enqueue(new Callback<Events>() {
//            @Override
//            public void onResponse(Call<Events> call, Response<Events> response) {
//                Events events = response.body();
//
//                for (int i = 0; i < events.events.size(); ++i)
//                    adapter.add(new FakeModel().setUrl(events.events.get(i).eventCoverPicture), PlacePresenter.class);
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<Events> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

//        RequestProvider.getMockedGooglePlaces().enqueue(new Callback<Nearby>() {
//            @Override
//            public void onResponse(Call<Nearby> call, Response<Nearby> response) {
//                Nearby nearby = response.body();
//
//                String url = "";
//                for (int i = 0; i < nearby.results.size(); ++i) {
//                    List<Photo> photos = nearby.results.get(i).photos;
//                    if(!CollectionExtensions.isEmpty(photos) )
//                        if(photos.get(0).html_attributions != null && photos.get(0).html_attributions.length >= 1)
//                            url = photos.get(0).html_attributions[0];
//
//                    adapter.add(new FakeModel().setUrl(url), PlacePresenter.class);
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<Nearby> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
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
        RequestProvider.getEvents()
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
        return tag();
    }

    @NonNull
    @Override
    public String getScreenName() {
        return tag();
    }
}

package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import net.kibotu.android.recyclerviewpresenter.PresenterAdapter;
import net.kibotu.berlinplaces.FragmentProvider;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.facebook.events.Events;
import net.kibotu.berlinplaces.models.fake.FakeModel;
import net.kibotu.berlinplaces.network.RequestProvider;
import net.kibotu.berlinplaces.ui.BaseFragment;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class PlacesFragment extends BaseFragment {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    PresenterAdapter<FakeModel> adapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_places;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        adapter = new PresenterAdapter<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((item, view, position) -> FragmentProvider.showPlace(item));

        RequestProvider.getNearbyEvents(52.520645, 13.409779, 1000, "venue").enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Events events = response.body();
                for (int i = 0; i < events.events.size(); ++i)
                    adapter.add(new FakeModel().setUrl(events.events.get(i).eventCoverPicture), PlacePresenter.class);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                t.printStackTrace();
            }
        });

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

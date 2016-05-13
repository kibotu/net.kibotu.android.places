package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.common.android.utils.ui.recyclerView.PresenterAdapter;

import net.kibotu.berlinplaces.FragmentProvider;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.network.RequestProvider;
import net.kibotu.berlinplaces.network.fake.FakeModel;
import net.kibotu.berlinplaces.network.models.events.Events;
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

//        RequestProvider.getPlaces(52.520645, 13.409779, 1000, "venue").enqueue(new Callback<Nearby>() {
//            @Override
//            public void onResponse(Call<Nearby> call, Response<Nearby> response) {
//                Logger.v(tag(), response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<Nearby> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

        RequestProvider.getPreloadedJson().enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Events events = response.body();

                for (int i = 0; i < events.events.size(); ++i)
                    adapter.add(new FakeModel().setUrl(events.events.get(i).venueCoverPicture), PlacePresenter.class);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                t.printStackTrace();
            }
        });

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

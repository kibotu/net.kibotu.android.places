package net.kibotu.berlinplaces.ui.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.common.android.utils.ui.recyclerView.PresenterAdapter;

import net.kibotu.berlinplaces.FragmentProvider;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.network.fake.FakeModel;
import net.kibotu.berlinplaces.ui.BaseFragment;
import net.kibotu.berlinplaces.utils.FakeDataGenerator;

import butterknife.BindView;

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

        for (int i = 0; i < 100; ++i)
            adapter.add(new FakeModel().setUrl(FakeDataGenerator.createRandomImageUrl()), PlacePresenter.class);

        adapter.setOnItemClickListener((item, view, position) -> FragmentProvider.showPlace(item));
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

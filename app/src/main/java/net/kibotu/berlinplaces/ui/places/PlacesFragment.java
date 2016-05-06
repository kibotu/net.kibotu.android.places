package net.kibotu.berlinplaces.ui.places;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.common.android.utils.ui.recyclerView.DataBindAdapter;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.ui.BaseFragment;

import butterknife.BindView;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class PlacesFragment extends BaseFragment {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    DataBindAdapter<String> adapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_places;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        adapter = new DataBindAdapter<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
        adapter.add("Test", PlacePresenter.class);
    }

    @NonNull
    @Override
    public String getTitle() {
        return tag();
    }

    @NonNull
    @Override
    public String getScreenName(@NonNull Context context) {
        return tag();
    }
}

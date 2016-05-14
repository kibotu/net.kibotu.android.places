package net.kibotu.berlinplaces.ui.places;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.utils.ContextHelper;
import com.common.android.utils.ui.recyclerView.Presenter;
import com.common.android.utils.ui.recyclerView.PresenterAdapter;
import com.squareup.picasso.Picasso;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.fake.FakeModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.common.android.utils.extensions.ViewExtensions.inflate;

/**
 * Created by jan.rabe on 06/05/16.
 */
public class PlacePresenter extends Presenter<FakeModel, PlacePresenter.ViewHolder> {

    public PlacePresenter(@NonNull PresenterAdapter<FakeModel> dataBindAdapter) {
        super(dataBindAdapter);
    }

    @NonNull
    @Override
    protected ViewHolder createViewHolder(int position, ViewGroup viewGroup) {
        return new ViewHolder(getLayout(), viewGroup);
    }

    @Override
    public void bindViewHolder(@NonNull ViewHolder viewHolder, @NonNull final FakeModel item, int position) {
        viewHolder.label.setText(item.url);

        Picasso.with(ContextHelper.getContext()).load(item.url).into(viewHolder.photo);

        viewHolder.itemView.setOnClickListener(v -> {
            if (presenterAdapter.getOnItemClickListener() != null)
                presenterAdapter.getOnItemClickListener().onItemClick(item, v, position);
        });
    }

    @Override
    public int getLayout() {
        return R.layout.place_item;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        @BindView(R.id.photo)
        ImageView photo;

        @NonNull
        @BindView(R.id.label)
        TextView label;

        public ViewHolder(@LayoutRes int layout, @Nullable ViewGroup parent) {
            super(inflate(layout, parent));
            ButterKnife.bind(this, itemView);
        }
    }
}

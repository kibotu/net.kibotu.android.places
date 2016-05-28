package net.kibotu.berlinplaces.ui.places;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.utils.ContextHelper;
import com.squareup.picasso.Picasso;

import net.kibotu.android.recyclerviewpresenter.Presenter;
import net.kibotu.android.recyclerviewpresenter.PresenterAdapter;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.events.Event;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;
import static com.common.android.utils.extensions.ViewExtensions.inflate;

/**
 * Created by jan.rabe on 06/05/16.
 */
public class PlacePresenter extends Presenter<Event, PlacePresenter.ViewHolder> {

    public PlacePresenter(@NonNull PresenterAdapter<Event> dataBindAdapter) {
        super(dataBindAdapter);
    }

    @NonNull
    @Override
    protected ViewHolder createViewHolder(int position, @NonNull ViewGroup viewGroup) {
        return new ViewHolder(getLayout(), viewGroup);
    }

    @Override
    public void bindViewHolder(@NonNull ViewHolder viewHolder, @NonNull final Event item, int position) {
        String url = item.picture;

        if (!isEmpty(url)) {
            viewHolder.label.setText(Html.fromHtml(url));
            Picasso.with(ContextHelper.getContext()).load(url).into(viewHolder.photo);
        }

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

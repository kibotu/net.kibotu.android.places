package net.kibotu.berlinplaces.ui.places;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.utils.ui.recyclerView.DataBindAdapter;
import com.common.android.utils.ui.recyclerView.DataBinder;

import net.kibotu.berlinplaces.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.common.android.utils.extensions.ViewExtensions.inflate;

/**
 * Created by jan.rabe on 06/05/16.
 */
public class PlacePresenter extends DataBinder<String, PlacePresenter.ViewHolder> {

    public PlacePresenter(@NonNull DataBindAdapter<String> dataBindAdapter) {
        super(dataBindAdapter);
    }

    @NonNull
    @Override
    protected ViewHolder createViewHolder(int position, ViewGroup viewGroup) {
        return new ViewHolder(getLayout(), viewGroup);
    }

    @Override
    public void bindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final String item = get(position);

        viewHolder.label.setText(item);
    }

    @Override
    public int getLayout() {
        return R.layout.place_item;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.label)
        TextView label;

        public ViewHolder(@LayoutRes int layout, @Nullable ViewGroup parent) {
            super(inflate(layout, parent));
            ButterKnife.bind(this, itemView);
        }
    }
}

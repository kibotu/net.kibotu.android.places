package net.kibotu.berlinplaces.ui.places;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.utils.interfaces.LayoutProvider;
import com.common.android.utils.interfaces.LogTag;

import net.kibotu.android.recyclerviewpresenter.BaseViewHolder;
import net.kibotu.android.recyclerviewpresenter.OnItemClickListener;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static java.text.MessageFormat.format;
import static net.kibotu.berlinplaces.ui.ViewHelper.loadCoverAndSetVibrantCardColor;

/**
 * Created by Nyaruhodo on 12.06.2016.
 */

public class SwipeStackAdapterEvents extends BaseAdapter implements LogTag, LayoutProvider {

    @NonNull
    private final List<Event> data;

    @NonNull
    public final Map<Integer, ViewHolder> viewHolderMap;

    @Nullable
    private OnItemClickListener<Event> onItemClickListener;

    public SwipeStackAdapterEvents(@Nullable List<Event> events) {
        viewHolderMap = new HashMap<>();
        data = events == null
                ? new ArrayList<>()
                : events;
    }

    @Override
    public int getLayout() {
        return R.layout.card;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
            viewHolderMap.put(position, new ViewHolder(convertView));
        }

        final Event item = data.get(position);

        final ViewHolder viewHolder = viewHolderMap.get(position);

        viewHolder.name.setText(item.name);
        viewHolder.page.setText(format("{0}/{1}", position, data.size()));

        loadCoverAndSetVibrantCardColor(viewHolder.itemView, viewHolder.cover, item.picture);

        viewHolder.cover.setOnClickListener(v -> {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(item, viewHolder.itemView, position);
        });

        return convertView;
    }

    @NonNull
    @Override
    public String tag() {
        return getClass().getSimpleName();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Event getItem(int position) {
        return data.get(position % data.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    public OnItemClickListener<Event> getOnItemClickListener() {
        return onItemClickListener;
    }

    public SwipeStackAdapterEvents setOnItemClickListener(@Nullable OnItemClickListener<Event> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.photo)
        ImageView cover;

        @BindView(R.id.stamp)
        TextView stamp;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.page)
        TextView page;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

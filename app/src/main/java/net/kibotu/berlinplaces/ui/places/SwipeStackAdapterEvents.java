package net.kibotu.berlinplaces.ui.places;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.utils.interfaces.LayoutProvider;
import com.common.android.utils.interfaces.LogTag;

import net.kibotu.android.recyclerviewpresenter.BaseViewHolder;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.events.Event;

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

    private final List<Event> data;
    public final Map<Integer, ViewHolder> viewHolderMap;

    public SwipeStackAdapterEvents(List<Event> events) {
        data = events;
        viewHolderMap = new HashMap<>();
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

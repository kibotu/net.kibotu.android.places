package net.kibotu.berlinplaces.ui.places;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.utils.interfaces.LayoutProvider;
import com.common.android.utils.interfaces.LogTag;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.Location;

import java.util.List;

import static com.common.android.utils.extensions.ViewExtensions.inflate;
import static net.kibotu.berlinplaces.ui.ViewHelper.loadCoverAndSetVibrantCardColor;

/**
 * Created by Nyaruhodo on 28.05.2016.
 */
public class SwipeStackAdapterPlaces extends BaseAdapter implements LogTag, LayoutProvider {

    private List<Location> data;

    public SwipeStackAdapterPlaces(List<Location> data) {
        this.data = data;
    }

    @Override
    public int getLayout() {
        return R.layout.card;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView = inflate(getLayout(), parent, false);
        final Location location = data.get(position);

        final TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(location.name);

        loadCoverAndSetVibrantCardColor(convertView.findViewById(R.id.card_layout), (ImageView) convertView.findViewById(R.id.photo), location.images.facebookCover);

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
    public Location getItem(int position) {
        return data.get(position % data.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

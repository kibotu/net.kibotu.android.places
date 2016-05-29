package net.kibotu.berlinplaces.ui.places;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.common.android.utils.ContextHelper;
import com.common.android.utils.interfaces.LogTag;
import com.common.android.utils.logging.Logger;

import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.paul.Location;

import java.util.List;

import static net.kibotu.berlinplaces.misc.Extensions.generateRandomColor;
import static net.kibotu.berlinplaces.misc.Extensions.generateRandomColorDrawable;

/**
 * Created by Nyaruhodo on 28.05.2016.
 */
public class SwipeStackAdapter extends BaseAdapter implements LogTag {

    private List<Location> mData;

    public SwipeStackAdapter(List<Location> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Location getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView = ContextHelper.getActivity().getLayoutInflater().inflate(R.layout.card, parent, false);
        final Location location = mData.get(position);

        String facebookCover = location.images.facebookCover;
        Logger.v(tag(), "[image] " + facebookCover);

        final TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(location.name);

        final ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);

        final View cardView = convertView.findViewById(R.id.card_layout);

        Glide.with(convertView.getContext())
                .load(facebookCover)
                .asBitmap()
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        cardView.setBackground(generateRandomColorDrawable());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Palette.from(resource).generate(palette -> {
                            int vibrantColor = palette.getLightVibrantColor(generateRandomColor());

                            Logger.v(tag(), "Vibrant: #" + Integer.toHexString(vibrantColor));

                            cardView.setBackground(new ColorDrawable(vibrantColor));
                        });
                        return false;
                    }
                })
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        return convertView;
    }

    @NonNull
    @Override
    public String tag() {
        return getClass().getSimpleName();
    }
}

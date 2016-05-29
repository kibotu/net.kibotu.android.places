package net.kibotu.berlinplaces.misc;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import static com.common.android.utils.ContextHelper.getContext;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */

public class Extensions {

    public static void setRoundedImage(@NonNull final ImageView imageView, @DrawableRes final int drawable) {
        Glide.with(getContext()).load(drawable).asBitmap().centerCrop().into(createRoundImageTarget(imageView));
    }

    public static void setRoundedImage(@NonNull final ImageView imageView, @NonNull final String url) {
        Glide.with(getContext()).load(url).asBitmap().centerCrop().into(createRoundImageTarget(imageView));
    }

    @NonNull
    private static BitmapImageViewTarget createRoundImageTarget(@NonNull final ImageView imageView) {
        return new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                final RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                drawable.setCircular(true);
                imageView.setImageDrawable(drawable);
            }
        };
    }
}

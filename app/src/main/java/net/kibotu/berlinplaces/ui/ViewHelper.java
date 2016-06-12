package net.kibotu.berlinplaces.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.common.android.utils.logging.Logger;

import rx.functions.Action1;

import static net.kibotu.berlinplaces.misc.Extensions.generateRandomColor;
import static net.kibotu.berlinplaces.misc.Extensions.generateRandomColorDrawable;
import static net.kibotu.berlinplaces.misc.SnackbarExtensions.showDangerSnack;

/**
 * Created by Nyaruhodo on 12.06.2016.
 */

public class ViewHelper {

    private static final String TAG = ViewHelper.class.getSimpleName();

    public static void loadCoverAndSetVibrantCardColor(View cardView, ImageView imageView, String imageUrl) {
        Logger.v(TAG, "[image] " + imageUrl);

        Glide.with(cardView.getContext())
                .load(imageUrl)
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
                            final int lightVibrantColor = palette.getLightVibrantColor(generateRandomColor());
                            cardView.setBackground(new ColorDrawable(lightVibrantColor));
                            imageView.setTag(lightVibrantColor);
                        });

                        return false;
                    }
                })
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @NonNull
    public static Action1<Throwable> showError() {
        return throwable -> {
            throwable.printStackTrace();
            showDangerSnack(throwable.getMessage());
        };
    }
}

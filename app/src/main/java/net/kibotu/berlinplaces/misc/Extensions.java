package net.kibotu.berlinplaces.misc;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.Random;

import static com.common.android.utils.ContextHelper.getActivity;
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

    public static int generateRandomColor() {
        return generateRandomColor(255, 255, 255, 255);
    }

    public static ColorDrawable generateRandomColorDrawable() {
        return generateRandomColorDrawable(255, 255, 255, 255);
    }

    public static ColorDrawable generateRandomColorDrawable(int alpha, int red, int green, int blue) {
        return new ColorDrawable(generateRandomColor(alpha, red, green, blue));
    }

    public static int generateRandomColor(int alpha, int red, int green, int blue) {

        final Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        // mix the color
        r = (r + red) / 2;
        g = (g + green) / 2;
        b = (b + blue) / 2;

        return Color.argb(alpha, r, g, b);
    }

    public static void changeStatusBarColor(final int color) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;

        final Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }
}

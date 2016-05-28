package net.kibotu.berlinplaces.ui.places;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.common.android.utils.interfaces.LogTag;
import com.common.android.utils.logging.Logger;

public class StretchableImageView extends ImageView implements LogTag {

    public StretchableImageView(Context context) {
        super(context);
    }

    public StretchableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StretchableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        Logger.v(tag(), "getDrawable null -> w=" + widthMeasureSpec + " h=" + heightMeasureSpec);

//
//        Drawable background = getBackground();
//        if (background == null)
//            background = getDrawable();
//
//        if (background == null) {
//            Logger.v(tag(), "getDrawable null -> w=" + widthMeasureSpec + " h=" + heightMeasureSpec);
//            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
//            return;
//        }
//
//        if (background.getIntrinsicWidth() >= getDrawable().getIntrinsicHeight()) {
//            int width = MeasureSpec.getSize(widthMeasureSpec);
//            int height = width * background.getIntrinsicHeight()
//                    / background.getIntrinsicWidth();
//            setMeasuredDimension(width, height);
//            Logger.v(tag(), "w > h w=" + width + " h=" + height);
//        } else {
//            int height = MeasureSpec.getSize(heightMeasureSpec);
//            int width = height * background.getIntrinsicWidth()
//                    / background.getIntrinsicHeight();
//            setMeasuredDimension(width, height);
//            Logger.v(tag(), "w < h w=" + width + " h=" + height);
//        }
    }

    @NonNull
    @Override
    public String tag() {
        return getClass().getSimpleName();
    }
}
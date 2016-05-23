package net.kibotu.berlinplaces.ui.places;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.stream.StreamUriLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.ImageVideoBitmapDecoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.common.android.utils.ContextHelper;
import com.common.android.utils.extensions.ViewExtensions;
import com.common.android.utils.interfaces.LogTag;
import com.common.android.utils.logging.Logger;
import com.squareup.picasso.Picasso;

import net.kibotu.android.recyclerviewpresenter.PresenterAdapter;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.facebook.events.Event;
import net.kibotu.berlinplaces.models.facebook.events.Events;
import net.kibotu.berlinplaces.models.paul.locations.Location;
import net.kibotu.berlinplaces.models.paul.locations.Locations;
import net.kibotu.berlinplaces.ui.BaseFragment;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;
import link.fls.swipestack.SwipeStack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.common.android.utils.extensions.ViewExtensions.setLayoutMargin;
import static com.common.android.utils.extensions.ViewExtensions.setOneTimeGlobalLayoutListener;
import static net.kibotu.berlinplaces.network.RequestProvider.getLocations;
import static net.kibotu.berlinplaces.network.RequestProvider.getNearbyEvents;

/**
 * Created by Nyaruhodo on 22.05.2016.
 */

public class PlacesStackFragment extends BaseFragment {

    @BindView(R.id.swipeStack)
    SwipeStack swipeStack;
    @BindView(R.id.list)
    RecyclerView list;

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        Logger.v(tag(), "onViewCreated ");

        list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        downloadNearby();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_places_stack;
    }

    private void downloadNearby() {
        getLocations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locations -> {
                    SwipeStackAdapter adapter = new SwipeStackAdapter(locations.locations);
                    swipeStack.setAdapter(adapter);
//                    PresenterAdapter<Location> adapter1 = new PresenterAdapter<Location>();
//                    list.setAdapter(adapter1);

                    adapter.notifyDataSetChanged();
                    Logger.v(tag(), "downloadNearby " + locations.locations);

                }, Throwable::printStackTrace);
    }

    public static class SwipeStackAdapter extends BaseAdapter implements LogTag {

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
//            textViewCard.setText(event.eventName);

            String facebookCover = location.images.facebookCover;
            Logger.v(tag(), "[image] " + facebookCover);

            final TextView name = (TextView) convertView.findViewById(R.id.name);
            name.setText(location.name);

            final StretchableImageView imageView = (StretchableImageView) convertView.findViewById(R.id.photo);

            ViewExtensions.setOneTimeGlobalLayoutListener(imageView, () -> {
                final ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                int i = (int) (imageView.getMeasuredWidth() / 3f);
                Logger.v(tag(), "i " + i);
                params.height = i;
                imageView.setLayoutParams(params);
                ViewExtensions.setOneTimeGlobalLayoutListener(imageView, () -> Glide.with(ContextHelper.getActivity())
                        .load(facebookCover)
                        .fitCenter()
                        .override(imageView.getMeasuredWidth(),Target.SIZE_ORIGINAL)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView));
            });
//                    .into(new BitmapImageViewTarget(imageView) {
//                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                            super.onResourceReady(resource, glideAnimation);
//
//                            final float aspect = (float) resource.getWidth() / resource.getHeight();
//                            int newHeight = (int) (imageView.getWidth() / aspect);
//                            Logger.v(tag(), "image w=" + imageView.getWidth() + " image h=" + imageView.getHeight());
//                            Logger.v(tag(), "resource w=" + resource.getWidth() + " resource h=" + resource.getHeight() + " aspect=" + aspect);
//                            Logger.v(tag(), "new w=" + imageView.getWidth() + " new h=" + newHeight);

//                            final ViewGroup.LayoutParams params = imageView.getLayoutParams();
//                            params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
//                            params.height = newHeight;
//                            imageView.setLayoutParams(params);
//                            setOneTimeGlobalLayoutListener(imageView, new ViewTreeObserver.OnGlobalLayoutListener() {
//                                @Override
//                                public void onGlobalLayout() {
//                                    Logger.v(tag(), "image3 w=" + imageView.getWidth() + " image3 h=" + imageView.getHeight());
//                                    imageView.setImageBitmap(resource);
//                                }
//                            });
//                        }
//                    });

            convertView
                    .findViewById(R.id.card_layout)
                    .setBackground(new ColorDrawable(Color.argb(255, getRandomColor(), getRandomColor(), getRandomColor())));

            return convertView;
        }

        private static int getRandomColor() {
            return (int) (Math.random() * 75) + 255 - 75;
        }

        @NonNull
        @Override
        public String tag() {
            return getClass().getSimpleName();
        }
    }
}

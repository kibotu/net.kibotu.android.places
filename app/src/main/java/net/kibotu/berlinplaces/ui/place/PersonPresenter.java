package net.kibotu.berlinplaces.ui.place;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dtx12.android_animations_actions.actions.Interpolations;

import net.kibotu.android.recyclerviewpresenter.BaseViewHolder;
import net.kibotu.android.recyclerviewpresenter.Presenter;
import net.kibotu.android.recyclerviewpresenter.PresenterAdapter;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.fake.person.Person;

import butterknife.BindView;

import static com.dtx12.android_animations_actions.actions.Actions.play;
import static com.dtx12.android_animations_actions.actions.Actions.scaleTo;
import static com.dtx12.android_animations_actions.actions.Actions.sequence;
import static net.kibotu.berlinplaces.misc.Extensions.setRoundedImage;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */
public class PersonPresenter extends Presenter<Person, PersonPresenter.ViewHolder> {

    public PersonPresenter(@NonNull PresenterAdapter<Person> presenterAdapter) {
        super(presenterAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.person_item;
    }

    @NonNull
    @Override
    protected ViewHolder createViewHolder(@LayoutRes int i, @NonNull ViewGroup viewGroup) {
        return new ViewHolder(i, viewGroup);
    }

    @Override
    public void bindViewHolder(@NonNull ViewHolder viewHolder, @NonNull Person person, int i) {
        setRoundedImage(viewHolder.image, person.picture.large);
        viewHolder.image.setOnClickListener(createZoomAnimationClickListener());
    }

    @NonNull
    private View.OnClickListener createZoomAnimationClickListener() {
        return v -> play(sequence(scaleTo(0.85f, 0.85f, 0.25f, Interpolations.BackEaseIn), scaleTo(1f, 1f, 0.15f, Interpolations.BackEaseOut)), v);
    }

    public static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.image)
        ImageView image;

        public ViewHolder(@LayoutRes int layout, @NonNull ViewGroup parent) {
            super(layout, parent);
        }
    }
}

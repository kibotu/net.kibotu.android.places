package net.kibotu.berlinplaces.ui.place;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.kibotu.android.recyclerviewpresenter.BaseViewHolder;
import net.kibotu.android.recyclerviewpresenter.Presenter;
import net.kibotu.android.recyclerviewpresenter.PresenterAdapter;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.fake.person.Person;

import butterknife.BindView;

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
    }

    public static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.image)
        ImageView image;

        public ViewHolder(@LayoutRes int layout, @NonNull ViewGroup parent) {
            super(layout, parent);
        }
    }
}

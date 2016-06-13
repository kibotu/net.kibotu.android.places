package net.kibotu.berlinplaces;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.kibotu.berlinplaces.models.paul.events.Event;
import net.kibotu.berlinplaces.ui.map.MapFragment;
import net.kibotu.berlinplaces.ui.place.PlaceFragment;
import net.kibotu.berlinplaces.ui.places.PlacesListFragment;
import net.kibotu.berlinplaces.ui.places.PlacesStackFragment;
import net.kibotu.berlinplaces.ui.stackOptions.StackOptions;

import org.parceler.Parcels;

import static com.common.android.utils.extensions.FragmentExtensions.replaceByFading;
import static com.common.android.utils.extensions.FragmentExtensions.replaceToBackStackBySlidingHorizontally;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class FragmentProvider {

    public static void showMap() {
        replaceByFading(new MapFragment());
    }

    public static void showPlaces() {
        replaceByFading(new PlacesListFragment());
    }

    public static void showPlace(@NonNull final Event item) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(Event.class.getSimpleName(), Parcels.wrap(item));
        final PlaceFragment fragment = new PlaceFragment();
        fragment.setArguments(bundle);
        replaceToBackStackBySlidingHorizontally(fragment);
    }

    public static void showPlacesStack(@Nullable final StackOptions options) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(StackOptions.class.getSimpleName(), Parcels.wrap(options));
        final PlacesStackFragment fragment = new PlacesStackFragment();
        fragment.setArguments(bundle);
        replaceToBackStackBySlidingHorizontally(fragment);
    }
}

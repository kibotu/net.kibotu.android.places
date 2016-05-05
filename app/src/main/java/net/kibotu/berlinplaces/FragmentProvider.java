package net.kibotu.berlinplaces;

import net.kibotu.berlinplaces.ui.map.MapFragment;

import static com.common.android.utils.extensions.FragmentExtensions.replaceByFading;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class FragmentProvider {

    public static void showMap() {
        replaceByFading(new MapFragment());
    }
}

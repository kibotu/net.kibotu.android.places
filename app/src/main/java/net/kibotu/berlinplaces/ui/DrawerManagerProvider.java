package net.kibotu.berlinplaces.ui;

import android.support.annotation.NonNull;

import net.kibotu.berlinplaces.ui.drawer.DrawerManager;

/**
 * Created by Nyaruhodo on 28.05.2016.
 */

public interface DrawerManagerProvider {

    @NonNull
    DrawerManager getDrawerManager();
}

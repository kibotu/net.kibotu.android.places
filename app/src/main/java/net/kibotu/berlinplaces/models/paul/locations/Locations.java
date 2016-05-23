package net.kibotu.berlinplaces.models.paul.locations;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Nyaruhodo on 23.05.2016.
 */

@Parcel
public class Locations {

    public List<Location> locations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locations locations1 = (Locations) o;

        return locations != null ? locations.equals(locations1.locations) : locations1.locations == null;

    }

    @Override
    public int hashCode() {
        return locations != null ? locations.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "locations=" + locations +
                '}';
    }
}

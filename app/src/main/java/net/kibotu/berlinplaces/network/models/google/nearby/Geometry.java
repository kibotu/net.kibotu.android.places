package net.kibotu.berlinplaces.network.models.google.nearby;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
@Parcel
public class Geometry {

    public Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;

        return location != null ? location.equals(geometry.location) : geometry.location == null;

    }

    @Override
    public int hashCode() {
        return location != null ? location.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "location=" + location +
                '}';
    }
}

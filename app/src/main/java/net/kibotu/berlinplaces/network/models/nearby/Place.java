package net.kibotu.berlinplaces.network.models.nearby;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class Place {

    public Geometry geometry;

    public String icon;

    public String id;

    public String name;

    public OpeningHours opening_hours;

    public List<Photo> photos;

    public String place_id;

    public float rating;

    public String reference;

    public String scope;

    public String[] types;

    public String vicinity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (Float.compare(place.rating, rating) != 0) return false;
        if (geometry != null ? !geometry.equals(place.geometry) : place.geometry != null)
            return false;
        if (icon != null ? !icon.equals(place.icon) : place.icon != null) return false;
        if (id != null ? !id.equals(place.id) : place.id != null) return false;
        if (name != null ? !name.equals(place.name) : place.name != null) return false;
        if (opening_hours != null ? !opening_hours.equals(place.opening_hours) : place.opening_hours != null)
            return false;
        if (photos != null ? !photos.equals(place.photos) : place.photos != null) return false;
        if (place_id != null ? !place_id.equals(place.place_id) : place.place_id != null)
            return false;
        if (reference != null ? !reference.equals(place.reference) : place.reference != null)
            return false;
        if (scope != null ? !scope.equals(place.scope) : place.scope != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(types, place.types)) return false;
        return vicinity != null ? vicinity.equals(place.vicinity) : place.vicinity == null;

    }

    @Override
    public int hashCode() {
        int result = geometry != null ? geometry.hashCode() : 0;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (opening_hours != null ? opening_hours.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        result = 31 * result + (place_id != null ? place_id.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(types);
        result = 31 * result + (vicinity != null ? vicinity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Place{" +
                "geometry=" + geometry +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", opening_hours=" + opening_hours +
                ", photos=" + photos +
                ", place_id='" + place_id + '\'' +
                ", rating=" + rating +
                ", reference='" + reference + '\'' +
                ", scope='" + scope + '\'' +
                ", types=" + Arrays.toString(types) +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }
}

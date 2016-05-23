package net.kibotu.berlinplaces.models.paul.locations;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Nyaruhodo on 23.05.2016.
 */
@Parcel
public class Types {

    public String facebookCategory; // ": "Attractions/Things to Do",
    public List<String> yelpCategories;
    public List<String> googleCategories;
    public List<String> foursquarCategories;
    public List<String> facebookSubcategories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Types types = (Types) o;

        if (facebookCategory != null ? !facebookCategory.equals(types.facebookCategory) : types.facebookCategory != null)
            return false;
        if (yelpCategories != null ? !yelpCategories.equals(types.yelpCategories) : types.yelpCategories != null)
            return false;
        if (googleCategories != null ? !googleCategories.equals(types.googleCategories) : types.googleCategories != null)
            return false;
        if (foursquarCategories != null ? !foursquarCategories.equals(types.foursquarCategories) : types.foursquarCategories != null)
            return false;
        return facebookSubcategories != null ? facebookSubcategories.equals(types.facebookSubcategories) : types.facebookSubcategories == null;

    }

    @Override
    public int hashCode() {
        int result = facebookCategory != null ? facebookCategory.hashCode() : 0;
        result = 31 * result + (yelpCategories != null ? yelpCategories.hashCode() : 0);
        result = 31 * result + (googleCategories != null ? googleCategories.hashCode() : 0);
        result = 31 * result + (foursquarCategories != null ? foursquarCategories.hashCode() : 0);
        result = 31 * result + (facebookSubcategories != null ? facebookSubcategories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Types{" +
                "facebookCategory='" + facebookCategory + '\'' +
                ", yelpCategories=" + yelpCategories +
                ", googleCategories=" + googleCategories +
                ", foursquarCategories=" + foursquarCategories +
                ", facebookSubcategories=" + facebookSubcategories +
                '}';
    }
}

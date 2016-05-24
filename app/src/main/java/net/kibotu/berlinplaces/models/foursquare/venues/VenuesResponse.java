package net.kibotu.berlinplaces.models.foursquare.venues;

import java.util.List;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class VenuesResponse {

    public List<Venue> venues;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VenuesResponse that = (VenuesResponse) o;

        return venues != null ? venues.equals(that.venues) : that.venues == null;

    }

    @Override
    public int hashCode() {
        return venues != null ? venues.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "VenuesResponse{" +
                "venues=" + venues +
                '}';
    }
}

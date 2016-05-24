package net.kibotu.berlinplaces.models.foursquare.venues;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class Venues {

    public Meta meta;

    public VenuesResponse response;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venues venues = (Venues) o;

        if (meta != null ? !meta.equals(venues.meta) : venues.meta != null) return false;
        return response != null ? response.equals(venues.response) : venues.response == null;

    }

    @Override
    public int hashCode() {
        int result = meta != null ? meta.hashCode() : 0;
        result = 31 * result + (response != null ? response.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Venues{" +
                "meta=" + meta +
                ", response=" + response +
                '}';
    }
}

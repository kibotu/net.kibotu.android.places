package net.kibotu.berlinplaces.models.fake.person;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */
@Parcel
public class Location {

    public String street; // ": "2490 ronda de toledo",
    public String city; // ": "zaragoza",
    public String state; // ": "cataluña",
    public String postcode; // ": 52272

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (street != null ? !street.equals(location.street) : location.street != null)
            return false;
        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        if (state != null ? !state.equals(location.state) : location.state != null) return false;
        return postcode != null ? postcode.equals(location.postcode) : location.postcode == null;

    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}

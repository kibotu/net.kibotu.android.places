package net.kibotu.berlinplaces.network.models.facebook.events;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 14.05.2016.
 */
@Parcel
public class VenueLocation {

    public String city;
    public String country;
    public double latitude;
    public double longitude;
    public String street;
    public int zip;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VenueLocation that = (VenueLocation) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.longitude, longitude) != 0) return false;
        if (zip != that.zip) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        return street != null ? street.equals(that.street) : that.street == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = city != null ? city.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + zip;
        return result;
    }

    @Override
    public String toString() {
        return "VenueLocation{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", street='" + street + '\'' +
                ", zip=" + zip +
                '}';
    }
}

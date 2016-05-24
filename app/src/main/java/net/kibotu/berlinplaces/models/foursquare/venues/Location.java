package net.kibotu.berlinplaces.models.foursquare.venues;

import java.util.List;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class Location {

    public String address;
    public double lat;
    public double lng;
    public int distance;
    public String postalCode;

    /**
     * DE
     */
    public String cc;
    public String city;
    public String state;
    public String country;
    public List<String> formattedAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Double.compare(location.lat, lat) != 0) return false;
        if (Double.compare(location.lng, lng) != 0) return false;
        if (distance != location.distance) return false;
        if (address != null ? !address.equals(location.address) : location.address != null)
            return false;
        if (postalCode != null ? !postalCode.equals(location.postalCode) : location.postalCode != null)
            return false;
        if (cc != null ? !cc.equals(location.cc) : location.cc != null) return false;
        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        if (state != null ? !state.equals(location.state) : location.state != null) return false;
        if (country != null ? !country.equals(location.country) : location.country != null)
            return false;
        return formattedAddress != null ? formattedAddress.equals(location.formattedAddress) : location.formattedAddress == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = address != null ? address.hashCode() : 0;
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + distance;
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (cc != null ? cc.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (formattedAddress != null ? formattedAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", distance=" + distance +
                ", postalCode='" + postalCode + '\'' +
                ", cc='" + cc + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", formattedAddress=" + formattedAddress +
                '}';
    }
}

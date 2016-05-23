package net.kibotu.berlinplaces.models.paul.locations;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Nyaruhodo on 23.05.2016.
 */

@Parcel
public class Address {

    public String zip; // ": "10711",
    public String country; // ": "Germany",
    public String city; // ": "Berlin",
    public String street; // ": "KurfÃ¼rstendamm 130",
    public List<Double> latlng;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (zip != null ? !zip.equals(address.zip) : address.zip != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null)
            return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        return latlng != null ? latlng.equals(address.latlng) : address.latlng == null;

    }

    @Override
    public int hashCode() {
        int result = zip != null ? zip.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (latlng != null ? latlng.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", latlng=" + latlng +
                '}';
    }
}

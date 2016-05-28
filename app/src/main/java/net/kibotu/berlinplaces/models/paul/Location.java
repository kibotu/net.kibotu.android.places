package net.kibotu.berlinplaces.models.paul;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 23.05.2016.
 */

@Parcel
public class Location {

    @SerializedName("id")
    public String id; // "57410c960a862e62a6c7f95d",
    public String name; // ": "Aquanario - Europas grÃ¶sstes Wasser-Spektakel",
    public String objectId; // ": "Syj-C5hK0z",
    public int __v; // ": 31,
    public Images images;
    public Types types;
    public ThirdPartyIds thirdPartyIds;
    public Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (__v != location.__v) return false;
        if (id != null ? !id.equals(location.id) : location.id != null) return false;
        if (name != null ? !name.equals(location.name) : location.name != null) return false;
        if (objectId != null ? !objectId.equals(location.objectId) : location.objectId != null)
            return false;
        if (images != null ? !images.equals(location.images) : location.images != null)
            return false;
        if (types != null ? !types.equals(location.types) : location.types != null) return false;
        if (thirdPartyIds != null ? !thirdPartyIds.equals(location.thirdPartyIds) : location.thirdPartyIds != null)
            return false;
        return address != null ? address.equals(location.address) : location.address == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + __v;
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (types != null ? types.hashCode() : 0);
        result = 31 * result + (thirdPartyIds != null ? thirdPartyIds.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", objectId='" + objectId + '\'' +
                ", __v=" + __v +
                ", images=" + images +
                ", types=" + types +
                ", thirdPartyIds=" + thirdPartyIds +
                ", address=" + address +
                '}';
    }
}

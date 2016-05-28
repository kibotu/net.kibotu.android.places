package net.kibotu.berlinplaces.models.paul;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 23.05.2016.
 */
@Parcel
public class ThirdPartyIds {

    public String facebook; // ": "223308461091276"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThirdPartyIds that = (ThirdPartyIds) o;

        return facebook != null ? facebook.equals(that.facebook) : that.facebook == null;

    }

    @Override
    public int hashCode() {
        return facebook != null ? facebook.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ThirdPartyIds{" +
                "facebook='" + facebook + '\'' +
                '}';
    }
}

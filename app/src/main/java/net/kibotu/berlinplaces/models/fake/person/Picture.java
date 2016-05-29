package net.kibotu.berlinplaces.models.fake.person;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */

@Parcel
public class Picture {

    public String large; // ": "https://randomuser.me/api/portraits/women/11.jpg",
    public String medium; // ": "https://randomuser.me/api/portraits/med/women/11.jpg",
    public String thumbnail; // ": "https://randomuser.me/api/portraits/thumb/women/11.jpg"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (large != null ? !large.equals(picture.large) : picture.large != null) return false;
        if (medium != null ? !medium.equals(picture.medium) : picture.medium != null) return false;
        return thumbnail != null ? thumbnail.equals(picture.thumbnail) : picture.thumbnail == null;

    }

    @Override
    public int hashCode() {
        int result = large != null ? large.hashCode() : 0;
        result = 31 * result + (medium != null ? medium.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}

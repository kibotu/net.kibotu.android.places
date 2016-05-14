package net.kibotu.berlinplaces.models.google.nearby;

import org.parceler.Parcel;

import java.util.Arrays;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
@Parcel
public class Photo {

    public int width;

    public int height;

    public String[] html_attributions;

    public String photo_reference;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (width != photo.width) return false;
        if (height != photo.height) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(html_attributions, photo.html_attributions)) return false;
        return photo_reference != null ? photo_reference.equals(photo.photo_reference) : photo.photo_reference == null;

    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + Arrays.hashCode(html_attributions);
        result = 31 * result + (photo_reference != null ? photo_reference.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "width=" + width +
                ", height=" + height +
                ", html_attributions=" + Arrays.toString(html_attributions) +
                ", photo_reference='" + photo_reference + '\'' +
                '}';
    }
}

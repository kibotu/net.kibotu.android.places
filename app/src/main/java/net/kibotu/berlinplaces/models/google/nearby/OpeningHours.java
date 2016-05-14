package net.kibotu.berlinplaces.models.google.nearby;

import org.parceler.Parcel;

import java.util.Arrays;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
@Parcel
public class OpeningHours {

    public boolean open_now;

    public String[] weekday_text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpeningHours that = (OpeningHours) o;

        if (open_now != that.open_now) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(weekday_text, that.weekday_text);

    }

    @Override
    public int hashCode() {
        int result = (open_now ? 1 : 0);
        result = 31 * result + Arrays.hashCode(weekday_text);
        return result;
    }

    @Override
    public String toString() {
        return "OpeningHours{" +
                "open_now=" + open_now +
                ", weekday_text=" + Arrays.toString(weekday_text) +
                '}';
    }
}

package net.kibotu.berlinplaces.models.facebook.friends;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class Summary {

    public int total_count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Summary summary = (Summary) o;

        return total_count == summary.total_count;

    }

    @Override
    public int hashCode() {
        return total_count;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "total_count=" + total_count +
                '}';
    }
}

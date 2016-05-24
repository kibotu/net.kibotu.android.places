package net.kibotu.berlinplaces.models.foursquare.venues;

import java.util.List;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class Specials {

    public int count;
    public List<Item> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Specials specials = (Specials) o;

        if (count != specials.count) return false;
        return items != null ? items.equals(specials.items) : specials.items == null;

    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Specials{" +
                "count=" + count +
                ", items=" + items +
                '}';
    }
}

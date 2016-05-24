package net.kibotu.berlinplaces.models.foursquare.venues;


import java.util.List;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class HereNow {

    public int count;
    public String summary;
    public List<Group> groups;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HereNow hereNow = (HereNow) o;

        if (count != hereNow.count) return false;
        if (summary != null ? !summary.equals(hereNow.summary) : hereNow.summary != null)
            return false;
        return groups != null ? groups.equals(hereNow.groups) : hereNow.groups == null;

    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HereNow{" +
                "count=" + count +
                ", summary='" + summary + '\'' +
                ", groups=" + groups +
                '}';
    }
}

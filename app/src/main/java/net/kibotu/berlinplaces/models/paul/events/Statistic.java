package net.kibotu.berlinplaces.models.paul.events;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 28.05.2016.
 */
@Parcel
public class Statistic {

    public int noreplyCount;
    public int maybeCount;
    public int declinedCount;
    public int attendingCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistic statistic = (Statistic) o;

        if (noreplyCount != statistic.noreplyCount) return false;
        if (maybeCount != statistic.maybeCount) return false;
        if (declinedCount != statistic.declinedCount) return false;
        return attendingCount == statistic.attendingCount;

    }

    @Override
    public int hashCode() {
        int result = noreplyCount;
        result = 31 * result + maybeCount;
        result = 31 * result + declinedCount;
        result = 31 * result + attendingCount;
        return result;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "noreplyCount=" + noreplyCount +
                ", maybeCount=" + maybeCount +
                ", declinedCount=" + declinedCount +
                ", attendingCount=" + attendingCount +
                '}';
    }
}

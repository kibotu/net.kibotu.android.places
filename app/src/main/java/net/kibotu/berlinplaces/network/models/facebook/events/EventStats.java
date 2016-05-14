package net.kibotu.berlinplaces.network.models.facebook.events;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 14.05.2016.
 */
@Parcel
public class EventStats {

    public int attendingCount;
    public int declinedCount;
    public int maybeCount;
    public int noreplyCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventStats that = (EventStats) o;

        if (attendingCount != that.attendingCount) return false;
        if (declinedCount != that.declinedCount) return false;
        if (maybeCount != that.maybeCount) return false;
        return noreplyCount == that.noreplyCount;

    }

    @Override
    public int hashCode() {
        int result = attendingCount;
        result = 31 * result + declinedCount;
        result = 31 * result + maybeCount;
        result = 31 * result + noreplyCount;
        return result;
    }

    @Override
    public String toString() {
        return "EventStats{" +
                "attendingCount=" + attendingCount +
                ", declinedCount=" + declinedCount +
                ", maybeCount=" + maybeCount +
                ", noreplyCount=" + noreplyCount +
                '}';
    }
}

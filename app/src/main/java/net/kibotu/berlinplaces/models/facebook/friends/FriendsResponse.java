package net.kibotu.berlinplaces.models.facebook.friends;

import java.util.List;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class FriendsResponse {

    public List<Data> data;

    public Paging paging;

    public Summary summary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsResponse that = (FriendsResponse) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (paging != null ? !paging.equals(that.paging) : that.paging != null) return false;
        return summary != null ? summary.equals(that.summary) : that.summary == null;

    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (paging != null ? paging.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendsResponse{" +
                "data=" + data +
                ", paging=" + paging +
                ", summary=" + summary +
                '}';
    }
}

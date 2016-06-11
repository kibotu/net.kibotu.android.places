package net.kibotu.berlinplaces.models.facebook.friends;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class Paging {

    public Cursors cursors;

    public String next;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paging paging = (Paging) o;

        if (cursors != null ? !cursors.equals(paging.cursors) : paging.cursors != null)
            return false;
        return next != null ? next.equals(paging.next) : paging.next == null;

    }

    @Override
    public int hashCode() {
        int result = cursors != null ? cursors.hashCode() : 0;
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "cursors=" + cursors +
                ", next='" + next + '\'' +
                '}';
    }
}

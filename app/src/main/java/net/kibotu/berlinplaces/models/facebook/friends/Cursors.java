package net.kibotu.berlinplaces.models.facebook.friends;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class Cursors {

    public String before;
    public String after;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cursors cursors = (Cursors) o;

        if (before != null ? !before.equals(cursors.before) : cursors.before != null) return false;
        return after != null ? after.equals(cursors.after) : cursors.after == null;

    }

    @Override
    public int hashCode() {
        int result = before != null ? before.hashCode() : 0;
        result = 31 * result + (after != null ? after.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cursors{" +
                "before='" + before + '\'' +
                ", after='" + after + '\'' +
                '}';
    }
}

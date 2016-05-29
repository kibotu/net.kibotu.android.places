package net.kibotu.berlinplaces.models.fake.person;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */

@Parcel
public class Name {

    public String title; // ": "miss",
    public String first; // ": "paula",
    public String last; // ": "parra"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (title != null ? !title.equals(name.title) : name.title != null) return false;
        if (first != null ? !first.equals(name.first) : name.first != null) return false;
        return last != null ? last.equals(name.last) : name.last == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (first != null ? first.hashCode() : 0);
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Name{" +
                "title='" + title + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}

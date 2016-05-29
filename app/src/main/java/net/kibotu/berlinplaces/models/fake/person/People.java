package net.kibotu.berlinplaces.models.fake.person;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */
@Parcel
public class People {

    public List<Person> results;

    public Info info;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        People people = (People) o;

        if (results != null ? !results.equals(people.results) : people.results != null)
            return false;
        return info != null ? info.equals(people.info) : people.info == null;

    }

    @Override
    public int hashCode() {
        int result = results != null ? results.hashCode() : 0;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "People{" +
                "results=" + results +
                ", info=" + info +
                '}';
    }
}

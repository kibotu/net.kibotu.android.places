package net.kibotu.berlinplaces.models.fake.person;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */

@Parcel
public class Info {

    public String seed; // ": "f27012848579e7b3",
    public int results; // ": 1,
    public int page; // ": 1,
    public String version; // ": "1.0"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (results != info.results) return false;
        if (page != info.page) return false;
        if (seed != null ? !seed.equals(info.seed) : info.seed != null) return false;
        return version != null ? version.equals(info.version) : info.version == null;

    }

    @Override
    public int hashCode() {
        int result = seed != null ? seed.hashCode() : 0;
        result = 31 * result + results;
        result = 31 * result + page;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Info{" +
                "seed='" + seed + '\'' +
                ", results=" + results +
                ", page=" + page +
                ", version='" + version + '\'' +
                '}';
    }
}

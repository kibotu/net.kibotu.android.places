package net.kibotu.berlinplaces.models.facebook.friends;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class Data {

    public String name;
    public String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (name != null ? !name.equals(data.name) : data.name != null) return false;
        return id != null ? id.equals(data.id) : data.id == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

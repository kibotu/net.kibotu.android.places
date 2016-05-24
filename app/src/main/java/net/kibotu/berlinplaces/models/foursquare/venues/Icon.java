package net.kibotu.berlinplaces.models.foursquare.venues;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class Icon {

    public String prefix;
    public String suffix;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Icon icon = (Icon) o;

        if (prefix != null ? !prefix.equals(icon.prefix) : icon.prefix != null) return false;
        return suffix != null ? suffix.equals(icon.suffix) : icon.suffix == null;

    }

    @Override
    public int hashCode() {
        int result = prefix != null ? prefix.hashCode() : 0;
        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Icon{" +
                "prefix='" + prefix + '\'' +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}

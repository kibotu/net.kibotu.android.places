package net.kibotu.berlinplaces.models.foursquare.venues;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class Meta {

    public int code;

    public String requestId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meta meta = (Meta) o;

        if (code != meta.code) return false;
        return requestId != null ? requestId.equals(meta.requestId) : meta.requestId == null;

    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "code=" + code +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}

package net.kibotu.berlinplaces.models.paul.blob;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class BlobResponse {

    public String blob;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlobResponse that = (BlobResponse) o;

        return blob != null ? blob.equals(that.blob) : that.blob == null;

    }

    @Override
    public int hashCode() {
        return blob != null ? blob.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BlobResponse{" +
                "blob='" + blob + '\'' +
                '}';
    }
}

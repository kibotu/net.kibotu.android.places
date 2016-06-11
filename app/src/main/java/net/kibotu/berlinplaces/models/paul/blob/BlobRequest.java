package net.kibotu.berlinplaces.models.paul.blob;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class BlobRequest {

    public String blob;

    public BlobRequest setBlob(String blob) {
        this.blob = blob;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlobRequest that = (BlobRequest) o;

        return blob != null ? blob.equals(that.blob) : that.blob == null;

    }

    @Override
    public int hashCode() {
        return blob != null ? blob.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BlobRequest{" +
                "blob='" + blob + '\'' +
                '}';
    }
}

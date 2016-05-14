package net.kibotu.berlinplaces.network.models.fake;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 07.05.2016.
 */
@Parcel
public class FakeModel {

    public static final String TAG = FakeModel.class.getSimpleName();

    public String url;

    public FakeModel setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FakeModel fakeModel = (FakeModel) o;

        return url != null ? url.equals(fakeModel.url) : fakeModel.url == null;

    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FakeModel{" +
                "url='" + url + '\'' +
                '}';
    }
}

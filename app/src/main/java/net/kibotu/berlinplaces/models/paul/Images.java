package net.kibotu.berlinplaces.models.paul;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 23.05.2016.
 */

@Parcel
public class Images {

    public String facebookProfil; // ": "https://scontent.xx.fbcdn.net/v/t1.0-1/p200x200/12310547_917232005032248_8363973292217070624_n.png?oh=94971d5855029618ccc404f6dfdbd2bd&oe=57D6AA81",
    public String facebookCover; // ": "https://scontent.xx.fbcdn.net/t31.0-8/s720x720/10620084_957191454369636_1024876574259876567_o.png"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Images images = (Images) o;

        if (facebookProfil != null ? !facebookProfil.equals(images.facebookProfil) : images.facebookProfil != null)
            return false;
        return facebookCover != null ? facebookCover.equals(images.facebookCover) : images.facebookCover == null;

    }

    @Override
    public int hashCode() {
        int result = facebookProfil != null ? facebookProfil.hashCode() : 0;
        result = 31 * result + (facebookCover != null ? facebookCover.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Images{" +
                "facebookProfil='" + facebookProfil + '\'' +
                ", facebookCover='" + facebookCover + '\'' +
                '}';
    }
}

package net.kibotu.berlinplaces.models.fake.person;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */
@Parcel
public class Login {

    public String username; // ": "bigdog599",
    public String password; // ": "fight",
    public String salt; // ": "DCnuYtrn",
    public String md5; // ": "2f8010d65e1c556502628f385610790f",
    public String sha1; // ": "9710dac5cb7195eee9322420a495f85831c072ab",
    public String sha256; // ": "0f91b11bde5ca9631345a11fff1ce3dc0333c2c5d13c7b90d315e2d256076687"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        if (username != null ? !username.equals(login.username) : login.username != null)
            return false;
        if (password != null ? !password.equals(login.password) : login.password != null)
            return false;
        if (salt != null ? !salt.equals(login.salt) : login.salt != null) return false;
        if (md5 != null ? !md5.equals(login.md5) : login.md5 != null) return false;
        if (sha1 != null ? !sha1.equals(login.sha1) : login.sha1 != null) return false;
        return sha256 != null ? sha256.equals(login.sha256) : login.sha256 == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (md5 != null ? md5.hashCode() : 0);
        result = 31 * result + (sha1 != null ? sha1.hashCode() : 0);
        result = 31 * result + (sha256 != null ? sha256.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", md5='" + md5 + '\'' +
                ", sha1='" + sha1 + '\'' +
                ", sha256='" + sha256 + '\'' +
                '}';
    }
}

package net.kibotu.berlinplaces.models.paul.login;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class AuthenticationResponse {

    public String uuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationResponse that = (AuthenticationResponse) o;

        return uuid != null ? uuid.equals(that.uuid) : that.uuid == null;

    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}

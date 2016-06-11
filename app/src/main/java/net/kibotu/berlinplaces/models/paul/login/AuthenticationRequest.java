package net.kibotu.berlinplaces.models.paul.login;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class AuthenticationRequest {

    public String credentials;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationRequest that = (AuthenticationRequest) o;

        return credentials != null ? credentials.equals(that.credentials) : that.credentials == null;

    }

    @Override
    public int hashCode() {
        return credentials != null ? credentials.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "credentials='" + credentials + '\'' +
                '}';
    }

    public AuthenticationRequest setCredentials(String credentials) {
        this.credentials = credentials;
        return this;
    }
}

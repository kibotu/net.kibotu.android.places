package net.kibotu.berlinplaces.network.models.nearby;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class Nearby {

    public String[] html_attributes;

    public String next_page_token;

    public List<Place> results;

    public String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nearby nearby = (Nearby) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(html_attributes, nearby.html_attributes)) return false;
        if (next_page_token != null ? !next_page_token.equals(nearby.next_page_token) : nearby.next_page_token != null)
            return false;
        if (results != null ? !results.equals(nearby.results) : nearby.results != null)
            return false;
        return status != null ? status.equals(nearby.status) : nearby.status == null;

    }

    @Override
    public int hashCode() {
        int result1 = Arrays.hashCode(html_attributes);
        result1 = 31 * result1 + (next_page_token != null ? next_page_token.hashCode() : 0);
        result1 = 31 * result1 + (results != null ? results.hashCode() : 0);
        result1 = 31 * result1 + (status != null ? status.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "Nearby{" +
                "html_attributes=" + Arrays.toString(html_attributes) +
                ", next_page_token='" + next_page_token + '\'' +
                ", results=" + results +
                ", status='" + status + '\'' +
                '}';
    }
}

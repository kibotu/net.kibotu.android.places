package net.kibotu.berlinplaces.models.foursquare.venues;

import java.util.List;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class Venue {

    public String id;
    public String name;
    public Contact contact;
    public Location location;
    public List<Category> categories;
    public boolean verified;
    public Statistic stats;
    public String url;
    public Specials specials;
    public HereNow hereNow;
    public String referralId;
    public List<VenueChain> venueChains;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venue venue = (Venue) o;

        if (verified != venue.verified) return false;
        if (id != null ? !id.equals(venue.id) : venue.id != null) return false;
        if (name != null ? !name.equals(venue.name) : venue.name != null) return false;
        if (contact != null ? !contact.equals(venue.contact) : venue.contact != null) return false;
        if (location != null ? !location.equals(venue.location) : venue.location != null)
            return false;
        if (categories != null ? !categories.equals(venue.categories) : venue.categories != null)
            return false;
        if (stats != null ? !stats.equals(venue.stats) : venue.stats != null) return false;
        if (url != null ? !url.equals(venue.url) : venue.url != null) return false;
        if (specials != null ? !specials.equals(venue.specials) : venue.specials != null)
            return false;
        if (hereNow != null ? !hereNow.equals(venue.hereNow) : venue.hereNow != null) return false;
        if (referralId != null ? !referralId.equals(venue.referralId) : venue.referralId != null)
            return false;
        return venueChains != null ? venueChains.equals(venue.venueChains) : venue.venueChains == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (verified ? 1 : 0);
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (specials != null ? specials.hashCode() : 0);
        result = 31 * result + (hereNow != null ? hereNow.hashCode() : 0);
        result = 31 * result + (referralId != null ? referralId.hashCode() : 0);
        result = 31 * result + (venueChains != null ? venueChains.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact=" + contact +
                ", location=" + location +
                ", categories=" + categories +
                ", verified=" + verified +
                ", stats=" + stats +
                ", url='" + url + '\'' +
                ", specials=" + specials +
                ", hereNow=" + hereNow +
                ", referralId='" + referralId + '\'' +
                ", venueChains=" + venueChains +
                '}';
    }
}

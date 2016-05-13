package net.kibotu.berlinplaces.network.models.events;

/**
 * Created by Nyaruhodo on 14.05.2016.
 */
public class Event {

    public long venueId;

    public String venueName;

    /**
     * image url
     */
    public String venueCoverPicture;

    /**
     * image url
     */
    public String venueProfilePicture;
    public VenueLocation venueLocation;
    public long eventId;
    public String eventName;
    public String eventCoverPicture;
    public String eventProfilePicture;

    /**
     * Probably html
     */
    public String eventDescription;

    /**
     * Format: 2016-05-28T23:00:00+0200
     */
    public String eventStarttime;
    public int eventDistance;
    public int eventTimeFromNow;
    public EventStats eventStats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (venueId != event.venueId) return false;
        if (eventId != event.eventId) return false;
        if (eventDistance != event.eventDistance) return false;
        if (eventTimeFromNow != event.eventTimeFromNow) return false;
        if (venueName != null ? !venueName.equals(event.venueName) : event.venueName != null)
            return false;
        if (venueCoverPicture != null ? !venueCoverPicture.equals(event.venueCoverPicture) : event.venueCoverPicture != null)
            return false;
        if (venueProfilePicture != null ? !venueProfilePicture.equals(event.venueProfilePicture) : event.venueProfilePicture != null)
            return false;
        if (venueLocation != null ? !venueLocation.equals(event.venueLocation) : event.venueLocation != null)
            return false;
        if (eventName != null ? !eventName.equals(event.eventName) : event.eventName != null)
            return false;
        if (eventCoverPicture != null ? !eventCoverPicture.equals(event.eventCoverPicture) : event.eventCoverPicture != null)
            return false;
        if (eventProfilePicture != null ? !eventProfilePicture.equals(event.eventProfilePicture) : event.eventProfilePicture != null)
            return false;
        if (eventDescription != null ? !eventDescription.equals(event.eventDescription) : event.eventDescription != null)
            return false;
        if (eventStarttime != null ? !eventStarttime.equals(event.eventStarttime) : event.eventStarttime != null)
            return false;
        return eventStats != null ? eventStats.equals(event.eventStats) : event.eventStats == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (venueId ^ (venueId >>> 32));
        result = 31 * result + (venueName != null ? venueName.hashCode() : 0);
        result = 31 * result + (venueCoverPicture != null ? venueCoverPicture.hashCode() : 0);
        result = 31 * result + (venueProfilePicture != null ? venueProfilePicture.hashCode() : 0);
        result = 31 * result + (venueLocation != null ? venueLocation.hashCode() : 0);
        result = 31 * result + (int) (eventId ^ (eventId >>> 32));
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (eventCoverPicture != null ? eventCoverPicture.hashCode() : 0);
        result = 31 * result + (eventProfilePicture != null ? eventProfilePicture.hashCode() : 0);
        result = 31 * result + (eventDescription != null ? eventDescription.hashCode() : 0);
        result = 31 * result + (eventStarttime != null ? eventStarttime.hashCode() : 0);
        result = 31 * result + eventDistance;
        result = 31 * result + eventTimeFromNow;
        result = 31 * result + (eventStats != null ? eventStats.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "venueId=" + venueId +
                ", venueName='" + venueName + '\'' +
                ", venueCoverPicture='" + venueCoverPicture + '\'' +
                ", venueProfilePicture='" + venueProfilePicture + '\'' +
                ", venueLocation=" + venueLocation +
                ", eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventCoverPicture='" + eventCoverPicture + '\'' +
                ", eventProfilePicture='" + eventProfilePicture + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventStarttime='" + eventStarttime + '\'' +
                ", eventDistance=" + eventDistance +
                ", eventTimeFromNow=" + eventTimeFromNow +
                ", eventStats=" + eventStats +
                '}';
    }
}

package net.kibotu.berlinplaces.models.paul.events;

import com.google.gson.annotations.SerializedName;

import net.kibotu.berlinplaces.models.paul.Location;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Nyaruhodo on 28.05.2016.
 */
@Parcel
public class Event {

    @SerializedName("_id")
    public String id; //": "5741dfa139bf2da8dbf71b19",
    public String fbId; // ; ": "1754071968145253",
    public List<Double> latlng;
    public String description; // ": "Die lange BÃ¶hse Onkelz Nacht im Ballhaus Spandau!\n\nEndlich ist es wieder soweit!\nBei uns im Ballhaus Spandau findet die \"Lange BÃ¶hse Onkelz Nacht\" zum zweiten Mal statt!\n\nAb 21:30 Uhr heizen uns Dirk & Durstig (BÃ¶hse Onkels Coverband) so richtig ein und ab ca. 24:00 Uhr bringt DJ Arvid die Plattenteller mit BÃ¶hsen Onkelz Songs so richtig zum GlÃ¼hen!\n\nBeginn: 21.30 Uhr (Einlass bereits ab: 20:30 Uhr)\nEintritt: VVK: 10,00 Euro  / AK: 12,00 Euro\n\nTickets im VVK erhÃ¤ltlich im Online-Shop und wÃ¤hrend der Ã–ffnungszeiten an der Kasse des Ballhaus Spandau!\nNatÃ¼rlich auch am Veranstaltungstag (23.09.16) an der Abendkasse vom Ballhaus Spandau!\n\n\nInfo: www.Ballhaus-Spandau.club",
    public String picture; // ": "https://scontent.xx.fbcdn.net/t31.0-8/s720x720/13055926_682344188573246_6486783201715585036_o.png",
    public String name; // ": "Die lange Onkelz Nacht im Ballhaus Spandau! Mit Dirk & Durstig und DJ Arvid",
    public String objectId; // ": "rJto1Pk7",
    public String __v; // ": 0,
    public List<Location> location;
    public long startTime;
    public Statistic stats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (startTime != event.startTime) return false;
        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (fbId != null ? !fbId.equals(event.fbId) : event.fbId != null) return false;
        if (latlng != null ? !latlng.equals(event.latlng) : event.latlng != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null)
            return false;
        if (picture != null ? !picture.equals(event.picture) : event.picture != null) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (objectId != null ? !objectId.equals(event.objectId) : event.objectId != null)
            return false;
        if (__v != null ? !__v.equals(event.__v) : event.__v != null) return false;
        if (location != null ? !location.equals(event.location) : event.location != null)
            return false;
        return stats != null ? stats.equals(event.stats) : event.stats == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fbId != null ? fbId.hashCode() : 0);
        result = 31 * result + (latlng != null ? latlng.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (__v != null ? __v.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (int) (startTime ^ (startTime >>> 32));
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", fbId='" + fbId + '\'' +
                ", latlng=" + latlng +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", objectId='" + objectId + '\'' +
                ", __v='" + __v + '\'' +
                ", location=" + location +
                ", startTime=" + startTime +
                ", stats=" + stats +
                '}';
    }
}

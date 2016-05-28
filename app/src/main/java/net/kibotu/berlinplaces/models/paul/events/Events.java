package net.kibotu.berlinplaces.models.paul.events;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Nyaruhodo on 28.05.2016.
 */
@Parcel
public class Events {

    public List<Event> events;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Events events1 = (Events) o;

        return events != null ? events.equals(events1.events) : events1.events == null;

    }

    @Override
    public int hashCode() {
        return events != null ? events.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Events{" +
                "events=" + events +
                '}';
    }
}

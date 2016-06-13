package net.kibotu.berlinplaces.ui.stackOptions;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 13.06.2016.
 */
@Parcel
public class StackOptions {

    public double latitude;
    public double longitute;
    public double radius;

    /**
     * In millis.
     */
    public long startDateTime;

    /**
     * In Seconds.
     */
    public long timeOffset;

    public StackOptions setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public StackOptions setLongitute(double longitute) {
        this.longitute = longitute;
        return this;
    }

    public StackOptions setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public StackOptions setStartDateTime(long startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    public StackOptions setTimeOffset(long timeOffset) {
        this.timeOffset = timeOffset;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StackOptions that = (StackOptions) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.longitute, longitute) != 0) return false;
        if (Double.compare(that.radius, radius) != 0) return false;
        if (startDateTime != that.startDateTime) return false;
        return timeOffset == that.timeOffset;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitute);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (startDateTime ^ (startDateTime >>> 32));
        result = 31 * result + (int) (timeOffset ^ (timeOffset >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "StackOptions{" +
                "latitude=" + latitude +
                ", longitute=" + longitute +
                ", radius=" + radius +
                ", startDateTime=" + startDateTime +
                ", timeOffset=" + timeOffset +
                '}';
    }
}

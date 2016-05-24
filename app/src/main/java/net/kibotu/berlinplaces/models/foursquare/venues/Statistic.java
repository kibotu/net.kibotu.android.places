package net.kibotu.berlinplaces.models.foursquare.venues;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class Statistic {

    public int checkinsCount;
    public int usersCount;
    public int tipCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistic statistic = (Statistic) o;

        if (checkinsCount != statistic.checkinsCount) return false;
        if (usersCount != statistic.usersCount) return false;
        return tipCount == statistic.tipCount;

    }

    @Override
    public int hashCode() {
        int result = checkinsCount;
        result = 31 * result + usersCount;
        result = 31 * result + tipCount;
        return result;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "checkinsCount=" + checkinsCount +
                ", usersCount=" + usersCount +
                ", tipCount=" + tipCount +
                '}';
    }
}

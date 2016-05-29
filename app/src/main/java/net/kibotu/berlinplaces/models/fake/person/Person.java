package net.kibotu.berlinplaces.models.fake.person;

import org.parceler.Parcel;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */

@Parcel
public class Person {

    public String gender; // ": "female",
    public Name name;
    public Location location;
    public String email; // ": "paula.parra@example.com",
    public Login login;
    public long registered; //": 1336005680,
    public long dob; // ": 1181834708,
    public String phone; // ": "950-712-270",
    public String cell; //": "600-556-845",
    public Id id;
    public Picture picture;
    public String nat; // ": "ES"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (registered != person.registered) return false;
        if (dob != person.dob) return false;
        if (gender != null ? !gender.equals(person.gender) : person.gender != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (location != null ? !location.equals(person.location) : person.location != null)
            return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (login != null ? !login.equals(person.login) : person.login != null) return false;
        if (phone != null ? !phone.equals(person.phone) : person.phone != null) return false;
        if (cell != null ? !cell.equals(person.cell) : person.cell != null) return false;
        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (picture != null ? !picture.equals(person.picture) : person.picture != null)
            return false;
        return nat != null ? nat.equals(person.nat) : person.nat == null;

    }

    @Override
    public int hashCode() {
        int result = gender != null ? gender.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (int) (registered ^ (registered >>> 32));
        result = 31 * result + (int) (dob ^ (dob >>> 32));
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (cell != null ? cell.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (nat != null ? nat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "gender='" + gender + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", login=" + login +
                ", registered=" + registered +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", id=" + id +
                ", picture=" + picture +
                ", nat='" + nat + '\'' +
                '}';
    }
}

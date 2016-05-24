package net.kibotu.berlinplaces.models.foursquare.venues;

/**
 * Created by jan.rabe on 17/05/16.
 */
public class Contact {

    public String phone;
    public String formattedPhone;
    public String twitter;
    public String facebook;
    public String facebookName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (phone != null ? !phone.equals(contact.phone) : contact.phone != null) return false;
        if (formattedPhone != null ? !formattedPhone.equals(contact.formattedPhone) : contact.formattedPhone != null)
            return false;
        if (twitter != null ? !twitter.equals(contact.twitter) : contact.twitter != null)
            return false;
        if (facebook != null ? !facebook.equals(contact.facebook) : contact.facebook != null)
            return false;
        return facebookName != null ? facebookName.equals(contact.facebookName) : contact.facebookName == null;

    }

    @Override
    public int hashCode() {
        int result = phone != null ? phone.hashCode() : 0;
        result = 31 * result + (formattedPhone != null ? formattedPhone.hashCode() : 0);
        result = 31 * result + (twitter != null ? twitter.hashCode() : 0);
        result = 31 * result + (facebook != null ? facebook.hashCode() : 0);
        result = 31 * result + (facebookName != null ? facebookName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phone='" + phone + '\'' +
                ", formattedPhone='" + formattedPhone + '\'' +
                ", twitter='" + twitter + '\'' +
                ", facebook='" + facebook + '\'' +
                ", facebookName='" + facebookName + '\'' +
                '}';
    }
}

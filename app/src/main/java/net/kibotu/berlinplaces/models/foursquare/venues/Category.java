package net.kibotu.berlinplaces.models.foursquare.venues;


/**
 * Created by jan.rabe on 17/05/16.
 */
public class Category {

    public String id;
    public String name;
    public String pluralName;
    public String shortName;
    public Icon icon;
    public boolean primary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (primary != category.primary) return false;
        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (pluralName != null ? !pluralName.equals(category.pluralName) : category.pluralName != null)
            return false;
        if (shortName != null ? !shortName.equals(category.shortName) : category.shortName != null)
            return false;
        return icon != null ? icon.equals(category.icon) : category.icon == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pluralName != null ? pluralName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (primary ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pluralName='" + pluralName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", icon=" + icon +
                ", primary=" + primary +
                '}';
    }
}

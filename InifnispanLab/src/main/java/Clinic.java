import java.io.Serializable;

public class Clinic implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String location;
    private String type;
    private int rating;

    public Clinic(String name, String location, String type, int rating) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Clinic: " + name + " Type:" + type + " Located in: " + location + " Rated: " + rating;
    }
}

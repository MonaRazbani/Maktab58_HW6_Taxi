package entity;

public class Location {
    String Coordinates;

    public Location(String coordinates) {
        Coordinates = coordinates;
    }

    public String getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(String coordinates) {
        Coordinates = coordinates;
    }
}

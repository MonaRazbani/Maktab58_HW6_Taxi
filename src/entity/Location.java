package entity;

public class Location {
    private String width;
    private String length;

    public Location() {
    }

    public Location(String width, String length) {
        this.width = width;
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}

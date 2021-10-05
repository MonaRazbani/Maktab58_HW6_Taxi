package entity.enums;

public enum Color {
    RED(1),WHITE(2),BLUE(3),BLACK(4),SILVER(5),GRAY(6),BEIGE(7);
    private int abbr ;

    Color(int abbr) {
        this.abbr = abbr;
    }

    public int getAbbr() {
        return abbr;
    }
}

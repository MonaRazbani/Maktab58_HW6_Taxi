package entity.enums;

public enum Gender {
    FEMALE(1), MAN(2);
    private int abbr ;

    Gender(int abbr) {
        this.abbr = abbr;
    }

    public int getAbbr() {
        return abbr;
    }
}
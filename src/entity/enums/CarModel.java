package entity.enums;

public enum CarModel {
    PEUGEOT206 (1), PRIDE(2) , PEUGEOT405(3) , TIBA(4),NULL(6);

    private int abbr ;

    CarModel(int abbr) {
        this.abbr = abbr;

}

    public int getAbbr() {
        return abbr;
    }
    }

package entity.enums;

public enum TypeOfVehicle {
    MOTORCYCLE(1),SMALLVAN(2),VAN(3),CAR(4);
    private int abbr ;

    TypeOfVehicle(int abbr) {
        this.abbr = abbr;
    }

    public int getAbbr() {
        return abbr;
    }
}

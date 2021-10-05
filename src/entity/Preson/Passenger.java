package entity.Preson;

import entity.Location;

import java.util.ArrayList;
import java.util.List;
public class Passenger<list> extends Person{
    private double wallet ;
    private List<Location> savedLocation =new ArrayList<>();
    private boolean tripStatus ;

    public Passenger(String firstName, String lastName, String userName, String password, String phoneNumber) {
        super(firstName, lastName, userName, password, phoneNumber);
        this.wallet = 0;
        this.tripStatus =false;
    }

    public double getWallet() {
        return wallet;
    }

    public List<Location> getSavedLocation() {
        return savedLocation;
    }

    public boolean isTripStatus() {
        return tripStatus;
    }
}

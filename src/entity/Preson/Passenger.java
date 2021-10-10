package entity.Preson;

import entity.Location;
import entity.Vehicle;
import entity.enums.Gender;

import java.util.ArrayList;
import java.util.List;
public class Passenger<list> extends Person{
    private double wallet ;
    private List<Location> savedLocation =new ArrayList<>();
    private boolean tripStatus ;
    public Passenger(int id , String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Location liveLocation, Gender gender) {
        super(id, firstName, lastName, userName, password, nationalCode, phoneNumber, liveLocation, gender);
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

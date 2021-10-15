package entity.Preson;

import entity.Location;
import entity.enums.Gender;
import entity.enums.TripStatusDriver;
import entity.enums.TripStatusPassenger;

import java.util.ArrayList;
import java.util.List;
public class Passenger<list> extends Person{
    private double wallet=0 ;
    private List<Location> savedLocation =new ArrayList<>();
    private TripStatusPassenger tripStatus ;
    public Passenger(String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Location liveLocation, Gender gender,double wallet,TripStatusPassenger tripStatus) {
        super(firstName, lastName, userName, password, nationalCode, phoneNumber, liveLocation, gender);
        this.wallet=wallet;
        this.tripStatus =tripStatus;

    }

    public Passenger() {

    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public void setSavedLocation(List<Location> savedLocation) {
        this.savedLocation = savedLocation;
    }

    public TripStatusPassenger getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatusPassenger tripStatus) {
        this.tripStatus = tripStatus;
    }

    public double getWallet() {
        return wallet;
    }

    public List<Location> getSavedLocation() {
        return savedLocation;
    }


    @Override
    public String toString() {
        return  getFirstName() +
                getLastName()+
                "wallet : " + wallet +
                ", tripStatusDriver:"+getTripStatus() ;
    }
}

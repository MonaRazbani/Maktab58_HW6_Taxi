package entity.Preson;

import entity.Location;
import entity.Vehicle;
import entity.enums.Gender;
import entity.enums.TripStatusDriver;

public class Driver extends Person{
    private double wallet ;
    private Vehicle vehicle;
    private TripStatusDriver tripStatusDriver;

    public Driver() {
        super();
    }


    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver(String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Location liveLocation, Gender gender, Vehicle vehicle, TripStatusDriver tripStatusDriver) {
        super(firstName,lastName,userName,password,nationalCode,phoneNumber,liveLocation,gender);
        this.vehicle = vehicle;
        this.tripStatusDriver = tripStatusDriver;
        this.wallet= 0 ;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public TripStatusDriver getTripStatusDriver() {
        return tripStatusDriver;
    }

    public void setTripStatusDriver(TripStatusDriver tripStatusDriver) {
        this.tripStatusDriver = tripStatusDriver;
    }

    public double getWallet() {
        return wallet;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return super.toString()+
                "wallet=" + wallet +
                ", vehicle=" + vehicle ;
    }
}

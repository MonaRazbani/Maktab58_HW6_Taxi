package entity.Preson;

import entity.Location;
import entity.Vehicle;
import entity.enums.Gender;

public class Driver extends Person{
    private double wallet ;
    private Vehicle vehicle;


    public Driver(int id , String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Location liveLocation, Gender gender, Vehicle vehicle ) {
        super(id,firstName,lastName,userName,password,nationalCode,phoneNumber,liveLocation,gender);
        this.vehicle = vehicle;
        this.wallet= 0 ;
    }

    public double getWallet() {
        return wallet;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

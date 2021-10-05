package entity.Preson;

import entity.Vehicle;

public class Driver extends Person{
    private double wallet ;
    private Vehicle vehicle;

    public Driver(String firstName, String lastName, String userName, String password, String phoneNumber, Vehicle vehicle) {
        super(firstName, lastName, userName, password, phoneNumber);
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

package entity;

import entity.Preson.Driver;
import entity.Preson.Passenger;
import entity.enums.PaymentStatus;
import entity.enums.TripStatus;

public class Trip {
    private Driver driver ;
    private Passenger passenger;
    private double cost ;
    private Location Destination ;
    private Location origin ;
    private TripStatus tripStatus ;
    private PaymentStatus paymentStatus;

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Trip(Driver driver, Passenger passenger, double cost, Location destination, Location origin) {
        this.driver = driver;
        this.passenger = passenger;
        this.cost = cost;
        Destination = destination;
        this.origin = origin;
        this.tripStatus= TripStatus.DOING;
        this.paymentStatus = PaymentStatus.NOT_PAID;
    }

    public Trip(Driver driver, Passenger passenger, double cost, Location destination, Location origin, TripStatus tripStatus, PaymentStatus paymentStatus) {
        this.driver = driver;
        this.passenger = passenger;
        this.cost = cost;
        Destination = destination;
        this.origin = origin;
        this.tripStatus = tripStatus;
        this.paymentStatus = paymentStatus;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Location getDestination() {
        return Destination;
    }

    public void setDestination(Location destination) {
        Destination = destination;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Trip: " +
                "driver :" + driver.getFirstName()+driver.getLastName()+"\n"+
                " passenger :" + passenger.getFirstName()+passenger.getUserName()+"\n"+
                " cost:" + cost +
                " Destination" + Destination +"to : "+
                " origin=" + origin +
                " tripStatus=" + tripStatus +
                " paymentStatus=" + paymentStatus ;
    }
}

package dataBaseAccess;

import entity.Location;
import entity.Preson.Passenger;
import entity.Preson.Driver;

import entity.Trip;
import entity.enums.PaymentStatus;
import entity.enums.TripStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TripDataBaseAccess extends DataBaseAccess {

    private DriverDataBaseAccess driverDao = new DriverDataBaseAccess();
    private PassengerDataBaseAccess passengerDao = new PassengerDataBaseAccess();
    private LocationDataBaseAccess locationDao = new LocationDataBaseAccess();

    public TripDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Trip trip) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            int driverId = driverDao.findDriverId(trip.getDriver());
            int passengerId = passengerDao.findPassengerId(trip.getPassenger());
            int destinationId = locationDao.FindLocationId(trip.getDestination());
            int originId = locationDao.FindLocationId(trip.getOrigin());
            int i = statement.executeUpdate(String.format("INSERT INTO `trip` (`passenger` , `driver` , `trip_status`, `payment_status`, `cast` , `origin` , `destination` ) VALUES('%d','%d','%s','%s','%f','%d','%d')"
                    , passengerId, driverId, trip.getTripStatus(), trip.getPaymentStatus(), trip.getCost(),originId,destinationId ));
            return i;

        } else return -1;
    }

    public Trip findByDriverNationalCode(String driverNationalCode) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            int driverId = driverDao.findIdDriverByNationalCode(driverNationalCode);
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * from trip where `driver` ='%d' ", driverId));
            if (resultSet.next()) {
                if (!resultSet.getString(4).equals("FINISH")) {
                    int passengerId = resultSet.getInt("passenger");
                    Passenger passenger = passengerDao.findPassengerById(passengerId);
                    Driver driver = driverDao.findDriverById(driverId);
                    int originId = resultSet.getInt("origin");
                    Location origin = locationDao.findById(originId);
                    int destinationId = resultSet.getInt("destination");
                    Location destination = locationDao.findById(destinationId);
                    TripStatus tripStatus = TripStatus.valueOf(resultSet.getString("trip_status"));
                    PaymentStatus paymentStatus = PaymentStatus.valueOf(resultSet.getString("payment_status"));
                    int cost = resultSet.getInt("cost");
                    Trip trip = new Trip(driver, passenger, cost, destination, origin, tripStatus, paymentStatus);
                    return trip;
                }
            }
        }
        return null;
    }

    public Trip findByPassengerNationalCode(String passengerNationalCode) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            Passenger passenger = passengerDao.findPassengerByNationalCode(passengerNationalCode);
            int passengerId = passengerDao.findPassengerId(passenger);
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * from trip where `driver` ='%d' ", passengerId));
            if (resultSet.next()) {
                if (!resultSet.getString(4).equals("FINISH")) {
                    int driverId  = resultSet.getInt("driver");
                    Driver driver = driverDao.findDriverById(passengerId);
                    int originId = resultSet.getInt("origin");
                    Location origin = locationDao.findById(originId);
                    int destinationId = resultSet.getInt("destination");
                    Location destination = locationDao.findById(destinationId);
                    TripStatus tripStatus = TripStatus.valueOf(resultSet.getString("tripstatus"));
                    PaymentStatus paymentStatus = PaymentStatus.valueOf("payment_status");
                    int cost = resultSet.getInt("cost");
                    Trip trip = new Trip(driver, passenger, cost, destination, origin, tripStatus, paymentStatus);
                    return trip;
                }
            }
        }
        return null;
    }

    public int findtripId (Trip trip) throws SQLException{
        if (getConnection()!=null) {
            Statement statement = getConnection().createStatement();
            int driverId = driverDao.findDriverId(trip.getDriver());
            int passengerId = passengerDao.findPassengerId(trip.getPassenger());
            int originId = locationDao.FindLocationId(trip.getOrigin());
            int destinationId = locationDao.FindLocationId(trip.getDestination());
            ResultSet resultSet = statement.executeQuery(String.format("select `idtrip` from `trip` where `driver` = '%d' AND `passenger`= '%d' AND `origin` = '%d' AND `destination` = '%d' ", driverId, passengerId, originId, destinationId));
            if (resultSet.next())
                return resultSet.getInt(1);
        }
        return -1 ;
    }

    public boolean updatePaymentStatus(PaymentStatus newPaymentStatus, int tripId ) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(String.format("UPDATE `trip` SET `payment_status`  = '%s' WHERE `idtrip`  = '%d' ",newPaymentStatus,tripId));
            if (i==1 ){
                return  true ;
            }
        }
        return false;
    }

    public boolean updateTripStatus(TripStatus newTripStatus, int tripId ) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(String.format("UPDATE `trip` SET `trip_status`  = '%s' WHERE `idtrip`  = '%d' ",newTripStatus,tripId));
            if (i==1 ){
                return  true ;
            }
        }
        return false;
    }


}
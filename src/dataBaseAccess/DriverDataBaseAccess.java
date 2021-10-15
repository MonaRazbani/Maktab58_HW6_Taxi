package dataBaseAccess;

import entity.Location;
import entity.Preson.Driver;
import entity.Preson.Passenger;
import entity.Vehicle;
import entity.enums.Gender;
import entity.enums.TripStatusDriver;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverDataBaseAccess extends DataBaseAccess {
    private VehicleDataBaseAccess vehicleDao = new VehicleDataBaseAccess();
    private LocationDataBaseAccess locationDao = new LocationDataBaseAccess();

    public DriverDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Driver driver,int vehicleId,int locationID) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO`driver` ( `first_name`, `last_name`, `username`, `national_code`, `password`, `gender`, `phone`, `location`, `vehicle_fk`,`tripstatus`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%d','%s')",
                     driver.getFirstName(),driver.getLastName(),driver.getUserName(),driver.getNationalCode(),driver.getPassword(),driver.getGender(),
                     driver.getPhoneNumber(),locationID,vehicleId,driver.getTripStatusDriver());

            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }

    public Driver findDriverByNationalCode (String nationalCode ) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM `driver` where `national_code` = '%s'", nationalCode));
            if (resultSet.next()) {
                //iddriver, first_name, last_name, username, national_code, password, gender, phone, location, vehicle_fk, tripsatus,
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String password = resultSet.getString(6);
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                String phoneNumber = resultSet.getString("phone");
                int locationId = resultSet.getInt("location");
                Location location = locationDao.findById(locationId);
                int vehicleId = resultSet.getInt("vehicle_fk");
                String tripSatus = resultSet.getString("tripstatus");
                Vehicle vehicle = vehicleDao.findVehicleById(vehicleId);
                TripStatusDriver tripStatusDriver = TripStatusDriver.valueOf(tripSatus);
                Driver found = new Driver(firstName, lastName, userName, password, nationalCode, phoneNumber, location, gender, vehicle, tripStatusDriver);
                return found;
            }
        }
         return null;
    }

    public Driver findDriverById (int id ) throws SQLException {
        if (getConnection()!=null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select * from `driver` where `iddriver` = '%d'", id));
            if (resultSet.next()){
                String firstName =resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String password = resultSet.getString(6);
                String nationalCode= resultSet.getString("national_code");
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                String phoneNumber= resultSet.getString("phone");
                int locationId  = resultSet.getInt("location");
                Location location =locationDao.findById(locationId);
                int vehicleId = resultSet.getInt("vehicle_fk");
                String tripSatus = resultSet.getString("tripstatus");
                Vehicle vehicle= vehicleDao.findVehicleById(vehicleId);
                TripStatusDriver tripStatusDriver=TripStatusDriver.valueOf(tripSatus);
                Driver found = new Driver(firstName,lastName,userName,password,nationalCode,phoneNumber, location, gender,vehicle,tripStatusDriver);
                return found ;
            }
        }
        return null;
    }

    public int findDriverId (Driver driver) throws SQLException {
        int driverId = -1 ;
        if (getConnection()!=null) {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select `iddriver` from `driver` where `national_code` = '%s'", driver.getNationalCode()));
           if(resultSet.next())
            driverId = resultSet.getInt(1);
        }
        return driverId;

    }

    public int findIdDriverByNationalCode (String nationalCode) throws SQLException {
        if (getConnection()!=null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select `iddriver` from `driver` where `national_code` = %s ", nationalCode));
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        }
        return -1 ;
    }

    public boolean updateTripStatus (TripStatusDriver newTripStatusDriver,int  driverId ) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(String.format("UPDATE `driver` SET `tripstatus`  = '%s' WHERE `iddriver`  = '%d' ",newTripStatusDriver,driverId));
            if (i==1 ){
                return  true ;
            }
        }
        return false;
    }
    public List<Driver> FindDriverWaitForTrip() throws SQLException {
        List<Driver> driverList = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `driver`where `tripstatus`='WAIT_For_Trip'");
            while (resultSet.next()) {
                Driver driver =new Driver();
                driver.setFirstName(resultSet.getString("first_name"));
                driver.setLastName(resultSet.getString("last_name"));
                driver.setUserName(resultSet.getString("username"));
                driver.setNationalCode(resultSet.getString("national_code"));
                driver.setPassword(resultSet.getString("password"));
                driver.setPhoneNumber(resultSet.getString("phone"));
                int locationId = resultSet.getInt("location");
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                driver.setGender(gender);
                driver.setGender(gender);
                int vehicleId = resultSet.getInt("vehicle_fk");
                driver.setVehicle(vehicleDao.findVehicleById(vehicleId));
                driver.setLiveLocation(locationDao.findById(locationId));
                driverList.add(driver);
            }
            return driverList ;
        }
        else return Collections.emptyList();

    }
    public List<Driver> display() throws SQLException {
        List<Driver> driverList = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `driver`");
            while (resultSet.next()) {
                    Driver driver =new Driver();
                    driver.setFirstName(resultSet.getString("first_name"));
                    driver.setLastName(resultSet.getString("last_name"));
                    driver.setUserName(resultSet.getString("username"));
                    driver.setNationalCode(resultSet.getString("national_code"));
                    driver.setPassword(resultSet.getString("password"));
                    driver.setPhoneNumber(resultSet.getString("phone"));
                    int locationId = resultSet.getInt("location");
                    Gender gender = Gender.valueOf(resultSet.getString("gender"));
                    driver.setGender(gender);
                    driver.setGender(gender);
                    int vehicleId = resultSet.getInt("vehicle_fk");
                    driver.setVehicle(vehicleDao.findVehicleById(vehicleId));
                    driver.setLiveLocation(locationDao.findById(locationId));
                    driverList.add(driver);
                }
            return driverList ;
        }
        else return Collections.emptyList();

    }

}

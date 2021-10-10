package dataBaseAccess;

import entity.Location;
import entity.Preson.Driver;
import entity.Vehicle;
import entity.enums.Gender;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverDataBaseAccess extends DataBaseAccess {
    private VehicleDataBaseAccess vehicleDao = new VehicleDataBaseAccess();
    public DriverDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();

    }


    public int save(Driver driver) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO driver ( id ,first_name, last_name, username, password,national_code, gender, phone, " +
                    "location, vehicle_fk) VALUES ('%d','%s','%s','%S','%S','%s','%s','%s,'%s','%d')",
                     driver.getFirstName(),driver.getLastName(),driver.getUserName(),driver.getPassword(),driver.getNationalCode(),driver.getGender(),
                     driver.getPhoneNumber(), driver.getLiveLocation(),driver.getVehicle().getLicensePlate());

            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }

    public Driver findDriverByNationalCode (String nationalCode ) throws SQLException {
        if (getConnection()!=null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select * from driver where national_code = '%s')",nationalCode));
                int id = resultSet.getInt(1);
                String firstName =resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String password = resultSet.getString(6);
                String phoneNumber= resultSet.getString(7);
                String liveLocation=resultSet.getString(8);
                Location location = new Location(liveLocation);
                Gender gender = Gender.valueOf(resultSet.getString(9));
                int vehicleId = resultSet.getInt(10);
                Vehicle vehicle= vehicleDao.findVehicleById(vehicleId);
                Driver found = new Driver(id,firstName,lastName,userName,password,nationalCode,phoneNumber, location, gender,vehicle);
            return found ;

        }
        else return null;

    }
    public List<Driver> display() throws SQLException {
        if (getConnection() != null) {
            List<Driver> driverList = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from driver");
            while (resultSet.next()) {
                for (Driver driver : driverList) {
                    driver.setId( resultSet.getInt(1));
                    driver.setFirstName(resultSet.getString(2));
                    driver.setLastName(resultSet.getString(3));
                    driver.setUserName(resultSet.getString(4));
                    driver.setNationalCode(resultSet.getString(5));
                    driver.setPassword(resultSet.getString(6));
                    driver.setPhoneNumber(resultSet.getString(7));
                    String liveLocation = resultSet.getString(8);
                    Location location = new Location(liveLocation);
                    driver.setLiveLocation(location);
                    Gender gender = Gender.valueOf(resultSet.getString(9));
                    driver.setGender(gender);
                    int vehicleId = resultSet.getInt(10);
                    Vehicle vehicle = vehicleDao.findVehicleById(vehicleId);
                    driver.setGender(gender);
                    driverList.add(driver);
                }
            }
            return driverList;
        } else {
            return Collections.emptyList();
        }
    }

}

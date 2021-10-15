package dataBaseAccess;

import entity.Location;
import entity.Preson.Driver;
import entity.Preson.Passenger;
import entity.Vehicle;
import entity.enums.Gender;
import entity.enums.TripStatusDriver;
import entity.enums.TripStatusPassenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassengerDataBaseAccess extends DataBaseAccess{
    private LocationDataBaseAccess locationDao = new LocationDataBaseAccess();

    public PassengerDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }


    public int save(Passenger passenger,int locationId) throws SQLException{
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO `passenger` (`first_name`,`last_name`,`username`,`password`,`national_code`,`gender`,`phone`,`wallet`,`tripstatus`,`location`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%f','%s','%d')",
                                                                       passenger.getFirstName(),passenger.getLastName(),passenger.getUserName(),passenger.getPassword(),passenger.getNationalCode(),
                                                                       passenger.getGender(),passenger.getPhoneNumber(),passenger.getWallet(),passenger.getTripStatus(),locationId);
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }

    public Passenger findPassengerById (int id )throws SQLException{

        if (getConnection()!=null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select * from `passenger` where `idpassenger` = '%d'",id));
            if (resultSet.next()) {
                //idpassenger, first_name, last_name, username, password, national_code, gender, phone, wallet, tripstatus, location
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String nationalCode = resultSet.getString("national_code");
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                String phoneNumber = resultSet.getString("phone");
                TripStatusPassenger tripStatus = TripStatusPassenger.valueOf(resultSet.getString("tripstatus"));
                double wallet = resultSet.getDouble("wallet");
                int locationId = resultSet.getInt("location");
                Location location = locationDao.findById(locationId);
                Passenger found = new Passenger(firstName, lastName, userName, password, nationalCode, phoneNumber, location, gender, wallet, tripStatus);
                return found;
            }
            else return null;
        }
        else return null;
    }

    public Passenger findPassengerByNationalCode (String nationalCode ) throws SQLException {
        if (getConnection()!=null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM `passenger` where `national_code` ='%s'",nationalCode));
            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                String phoneNumber = resultSet.getString("phone");
                TripStatusPassenger tripStatus = TripStatusPassenger.valueOf(resultSet.getString("tripstatus"));
                double wallet = resultSet.getDouble("wallet");
                int locationId = resultSet.getInt("location");
                Location location = locationDao.findById(locationId);
                Passenger found = new Passenger(firstName, lastName, userName, password, nationalCode, phoneNumber, location, gender, wallet, tripStatus);
                return found;
            }

            else return null;
        }
        else return null;

    }

    public int findPassengerId (Passenger passenger) throws SQLException {
        int passengerId = -1 ;
            if (getConnection()!=null) {
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(String.format("select `idpassenger` from `passenger` where `national_code` = '%s'", passenger.getNationalCode()));
                if (resultSet.next())
                passengerId = resultSet.getInt(1);
            }
            return passengerId;

    }

    public boolean updateWalletInventory (int passengerId , double newWalletInventory) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(String.format("UPDATE `passenger` SET `wallet`  = '%f' WHERE `idpassenger`  = '%d' ",newWalletInventory,passengerId));
            if (i==1 ){
                return  true ;
            }
        }
        return false;
    }

    public boolean updateTripStatus (TripStatusPassenger newTripStatusPassenger, int  passengerId ) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(String.format("UPDATE `passenger` SET `tripstatus`  = '%s' WHERE `idpassenger`  = '%d' ",newTripStatusPassenger,passengerId));
            if (i==1 ){
                return  true ;
            }
        }
        return false;
    }

    public List<Passenger> display() throws SQLException {
        if (getConnection() != null) {
            List<Passenger> passengerArrayList = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `passenger`");
                while (resultSet.next()) {
                        Passenger passenger =new Passenger();
                        passenger.setFirstName(resultSet.getString("first_name"));
                        passenger.setLastName(resultSet.getString("last_name"));
                        passenger.setUserName(resultSet.getString("user_name"));
                        passenger.setPassword(resultSet.getString("password"));
                        passenger.setNationalCode(resultSet.getString("national_code"));
                        Gender gender = Gender.valueOf(resultSet.getString("gender"));
                        passenger.setGender(gender);
                        passenger.setPhoneNumber(resultSet.getString("phone_number"));
                        passenger.setTripStatus(TripStatusPassenger.valueOf(resultSet.getString("tripstatus")));
                        passenger.setWallet(resultSet.getDouble("wallet"));
                        int locationId = resultSet.getInt("location");
                        Location location = locationDao.findById(locationId);
                        passenger.setLiveLocation(location);
                        passengerArrayList.add(passenger);
                }
                return passengerArrayList;
            }
             else {
                return Collections.emptyList();
            }
        }
  }




package dataBaseAccess;

import entity.Location;
import entity.Preson.Driver;
import entity.Preson.Passenger;
import entity.Vehicle;
import entity.enums.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassengerDataBaseAccess extends DataBaseAccess{
    public PassengerDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }
    public int save(Passenger passenger) throws SQLException{
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO passenger (id, first_name, last_name, username,password,national_code, gender, phone," +
                            " wallet, tripstatus, location) VALUES ('%s','%s','%s','%s','%d','%s','%d','%b','%s')",
                              passenger.getFirstName(),passenger.getLastName(),passenger.getUserName(),passenger.getPassword(),passenger.getNationalCode(),
                              passenger.getGender().getAbbr(),passenger.getPhoneNumber(),passenger.getWallet(),passenger.isTripStatus());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }

    public Passenger findPassengerByNationalCode (String nationalCode ) throws SQLException {
        if (getConnection()!=null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select * from passenger where national_code = '%s')",nationalCode));
            int id = resultSet.getInt(1);
            String firstName =resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String userName = resultSet.getString(4);
            String password = resultSet.getString(6);
            String phoneNumber= resultSet.getString(7);
            String liveLocation = resultSet.getString(8);
            Location location = new Location(liveLocation);
            Gender gender = Gender.valueOf(resultSet.getString(9));
            Passenger found = new Passenger(id,firstName,lastName,userName,password,nationalCode,phoneNumber,location,gender);
            return found ;

        }
        else return null;

    }

    public List<Passenger> display() throws SQLException {
        if (getConnection() != null) {
            List<Passenger> passengerArrayList = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from driver");
                while (resultSet.next()) {
                    for (Passenger passenger : passengerArrayList) {
                        passenger.setId(resultSet.getInt(1));
                        passenger.setFirstName(resultSet.getString(2));
                        passenger.setLastName(resultSet.getString(3));
                        passenger.setUserName(resultSet.getString(4));
                        passenger.setNationalCode(resultSet.getString(5));
                        passenger.setPassword(resultSet.getString(6));
                        passenger.setPhoneNumber(resultSet.getString(7));
                        String liveLocation = resultSet.getString(8);
                        Location location = new Location(liveLocation);
                        passenger.setLiveLocation(location);
                        Gender gender = Gender.valueOf(resultSet.getString(9));
                        passenger.setGender(gender);
                        passengerArrayList.add(passenger);
                    }
                }
                return passengerArrayList;
            }
             else {
                return Collections.emptyList();
            }
        }
  }




package dataBaseAccess;

import entity.Preson.Driver;
import entity.Preson.Passenger;

import java.sql.SQLException;
import java.sql.Statement;

public class PassengerDataBaseAccess extends DataBaseAccess{
    public PassengerDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }
    public int save(Passenger passenger) throws SQLException{
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO passenger (id, first_name, last_name, username, password, gender, phone," +
                            " wallet, tripstatus, location) VALUES ('%s','%s','%s','%s','%tF','%s','%d','%b','%s')",
                              passenger.getFirstName(),passenger.getLastName(),passenger.getUserName(),passenger.getPassword(),
                              passenger.getGender().getAbbr(),passenger.getPhoneNumber(),passenger.getWallet(),passenger.isTripStatus());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }
}

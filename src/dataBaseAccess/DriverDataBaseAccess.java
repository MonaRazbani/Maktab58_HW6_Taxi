package dataBaseAccess;

import entity.Preson.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverDataBaseAccess extends DataBaseAccess {
    public DriverDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }


    public int save(Driver driver) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO driver ( first_name, last_name, username, password, gender, phone, " +
                    "location, typeOfVehicle, car_model, vehiclecolor, vehicle_licence_plate) VALUES ('%s','%s','%S','%S','%tF,'%s','%s','%tF','%tF','%tF','%s' )",
                     driver.getFirstName(),driver.getLastName(),driver.getUserName(),driver.getPassword(),driver.getGender(),driver.getPhoneNumber(),
                     driver.getLiveLocation(),driver.getVehicle().getTypeOfVehicle().getAbbr(),driver.getVehicle().getModel().getAbbr(),
                     driver.getVehicle().getColor().getAbbr(),driver.getVehicle().getLicensePlate());

            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }
}

package dataBaseAccess;

import entity.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationDataBaseAccess extends DataBaseAccess{
    public LocationDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save (Location location) throws SQLException {
        if (getConnection()!=null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO `location` (`length` , `width` ) VALUES ( '%s' , '%s') ", location.getLength() ,location.getWidth());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }

    public int FindLocationId (Location location) throws SQLException {
        if (getConnection()!= null) {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select `id` from `location` where `width` = '%s' AND `length` = '%s'", location.getWidth(), location.getLength()));
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                return id;
            }
        }
        return -1 ;
    }

    public Location findById (int id ) throws SQLException {
        if (getConnection()!=null ){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select * from `location` where `id` = '%s'",id ));
                    if(resultSet.next()){
                        String width = resultSet.getString(3);
                        String length = resultSet.getString(2);
                        Location location=  new Location(width,length);
                        return location;
                    }

        }
        return null;
    }
}

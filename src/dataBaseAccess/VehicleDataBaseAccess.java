package dataBaseAccess;

import entity.Preson.Driver;
import entity.Vehicle;
import entity.enums.CarModel;
import entity.enums.Color;
import entity.enums.TypeOfVehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class VehicleDataBaseAccess extends DataBaseAccess {
    public VehicleDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Vehicle vehicle) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO vehicle ( idvehicle, Type_of_vehicle, color, model, license_plate) VALUES('%d','%s','%s','%s') ",
                                            vehicle.getId(),vehicle.getTypeOfVehicle(),vehicle.getColor(),vehicle.getModel(),vehicle.getLicensePlate()) ;

            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }
    public Vehicle findVehicleById (int id ) throws SQLException {
        if (getConnection()!=null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet =statement.executeQuery(String.format("SELECT * from vehicle where id = '%d '"+ id));
            TypeOfVehicle typeOfVehicle = TypeOfVehicle.valueOf(resultSet.getString(2));
            Color color= Color.valueOf(resultSet.getString(3));
            CarModel carModel = CarModel.valueOf(resultSet.getString(4));
            String licensePlate = resultSet.getString(5);
            Vehicle found = new Vehicle(id,licensePlate,typeOfVehicle,carModel,color);
            return found;
        }
        return null;
    }
}

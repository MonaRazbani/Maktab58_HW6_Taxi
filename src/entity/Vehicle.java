package entity;

import entity.enums.CarModel;
import entity.enums.Color;
import entity.enums.TypeOfVehicle;

public class Vehicle {
    private String licensePlate;
    private TypeOfVehicle typeOfVehicle;
    private CarModel model ;
    private Color color ;

    public Vehicle(String licensePlate, TypeOfVehicle typeOfVehicle, CarModel model, Color color) {
        this.licensePlate = licensePlate;
        this.typeOfVehicle = typeOfVehicle;
        this.model = model;
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public TypeOfVehicle getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public CarModel getModel() {
        return model;
    }

    public Color getColor() {
        return color;
    }
}

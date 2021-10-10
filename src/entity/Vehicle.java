package entity;

import entity.enums.CarModel;
import entity.enums.Color;
import entity.enums.TypeOfVehicle;

public class Vehicle {
    private int id ;
    private String licensePlate;
    private TypeOfVehicle typeOfVehicle;
    private CarModel model ;
    private Color color ;

    public Vehicle() {
    }

    public Vehicle(int id , String licensePlate, TypeOfVehicle typeOfVehicle, CarModel model, Color color) {
        this.id =id;
        this.licensePlate = licensePlate;
        this.typeOfVehicle = typeOfVehicle;
        this.model = model;
        this.color = color;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setTypeOfVehicle(TypeOfVehicle typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

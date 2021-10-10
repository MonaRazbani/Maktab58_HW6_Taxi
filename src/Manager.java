import dataBaseAccess.*;
import entity.*;
import entity.Preson.Driver;
import entity.Preson.Passenger;
import entity.enums.CarModel;
import entity.enums.Color;
import entity.enums.Gender;
import entity.enums.TypeOfVehicle;
import exceptions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Manager<list> {
    private DriverDataBaseAccess driverDao = new DriverDataBaseAccess();
    private PassengerDataBaseAccess passengerDao = new PassengerDataBaseAccess();
    private VehicleDataBaseAccess vehicleDao = new VehicleDataBaseAccess();
    private int driverAddNumber = 0 ;
    private int passengerAddNumber = 0 ;
    private int vehicleAddNumber = 0 ;


    static Scanner scanner = new Scanner(System.in);


    public Manager() throws SQLException, ClassNotFoundException {
    }

    public int getVehicleAddNumber() {
        return vehicleAddNumber;
    }

    public void plusVehicleAddNumber() {
        this.vehicleAddNumber ++;
    }

    public DriverDataBaseAccess getDriverDao() {
        return driverDao;
    }

    public PassengerDataBaseAccess getPassengerDao() {
        return passengerDao;
    }

    public int getDriverAddNumber() {
        return driverAddNumber;
    }

    public void plusDriverAddNumber() {
        this.driverAddNumber ++;
    }

    public int getPassengerAddNumber() {
        return passengerAddNumber;
    }

    public void plusPassengerAddNumber() {
        this.passengerAddNumber ++;
    }

    public void addDriver(String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Location liveLocation, Gender gender, Vehicle vehicle) throws SQLException {
        Driver driver = new Driver(getDriverAddNumber(), firstName, lastName, userName, password, nationalCode, phoneNumber, liveLocation, gender, vehicle);
        int count = driverDao.save(driver);
        if (count == 1) {
            System.out.println("Driver : " + driver + "saved!");
            plusDriverAddNumber();
        } else System.out.println("save fail !");
    }

    public void addGroupOfDriver(List<Driver> drivers) throws SQLException {
        for (Driver driver : drivers) {
            driverDao.save(driver);
        }
    }

    public void addPassenger(int id, String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Location liveLocation, Gender gender) throws SQLException {
        Passenger passenger = new Passenger(id, firstName, lastName, userName, password, nationalCode, phoneNumber, liveLocation, gender);
        int count = passengerDao.save(passenger);
        if (count == 1) {
            System.out.println("Passenger : " + passenger + "saved!");
        } else System.out.println("save fail !");
    }
    
    public void addVehicle(Vehicle vehicle) throws SQLException {
        int count =vehicleDao.save(vehicle);
        if (count == 1) {
            System.out.println("vehicle : " + vehicle + "saved!");
            plusVehicleAddNumber();
        } else System.out.println("save fail !");

    }
    
    public void addGroupOfPassenger(List<Passenger> passengers) throws SQLException {
        for (Passenger passenger : passengers) {
            passengerDao.save(passenger);

        }
    }

    public void driverLogin(String nationalCode, String password) throws SQLException {
        Driver driverLogin = driverDao.findDriverByNationalCode(nationalCode);
        if (driverLogin != null) {
            if (driverLogin.checkingPassword(nationalCode, password)) {
                System.out.println(" Hi Dear  " + driverLogin.getFirstName() + " " + driverLogin.getLastName());

            } else System.out.println("wrong username or password ");
        } else {
            Driver newDriver = singUpDriver();
            driverLogin(newDriver.getNationalCode(), newDriver.getPassword());
        }
    }

    public Driver singUpDriver() throws SQLException {
        System.out.println("enter first name : ");
        String firstName = getValidName();
        System.out.println("enter last name : ");
        String lastName = getValidName();
        System.out.println("enter password : ");
        String password = getValidPassword();
        System.out.println("enter national code ");
        String nationalCode = getValidNationalCode();
        String username = nationalCode;
        System.out.println("enter phone number : ");
        String phoneNumber = getValidPhoneNumber();
        Location liveLocation = null;
        System.out.println("enter gender : ");
        Gender gender = Gender.valueOf(getValidGender());
        System.out.println("enter type of vehicle :\nMOTORCYCLE , SMALLVAN , VAN , CAR ");
        TypeOfVehicle typeOfVehicle = TypeOfVehicle.valueOf(getValidTypeOFVehicl());
        System.out.println("enter color : \nRED , WHITE ,BLUE ,BLACK , SILVER ,GRAY ,BEIGE ");
        Color color = Color.valueOf(getValidColor());
        CarModel carModel;
        if (typeOfVehicle.getAbbr() == 4) {
            System.out.println("enter car model :\nPEUGEOT206 , PRIDE , PEUGEOT405 , TIBA ");
            carModel = CarModel.valueOf(getValidModel());
        } else {
            carModel = CarModel.valueOf("NULL");
        }

        System.out.println(" enter Licence Plate :");
        String licencePlate = getValidLicencePlate();
        Vehicle vehicleDriver = new Vehicle(getVehicleAddNumber(), licencePlate, typeOfVehicle, carModel, color);
        Driver driver = new Driver(getDriverAddNumber(), firstName, lastName, username, password, nationalCode, phoneNumber, liveLocation, gender, vehicleDriver);
        driverDao.save(driver);
        plusDriverAddNumber();
        addVehicle(vehicleDriver);
        return driver;
    }

    public void passengerLogin(String nationalCode, String password) throws SQLException {
        Driver driverLogin = driverDao.findDriverByNationalCode(nationalCode);
        if (driverLogin != null) {
            if (driverLogin.checkingPassword(nationalCode, password)) {
                System.out.println(" Hi Dear  " + driverLogin.getFirstName() + " " + driverLogin.getLastName());

            } else System.out.println("wrong username or password ");
        } else {
            Passenger newPassenger = signUpPassenger();
            driverLogin(newPassenger.getNationalCode(), newPassenger.getPassword());
        }
    }

    public Passenger signUpPassenger() throws SQLException {
        System.out.println("enter first name : ");
        String firstName = getValidName();
        System.out.println("enter last name : ");
        String lastName = getValidName();
        System.out.println("enter password : ");
        String password = getValidPassword();
        System.out.println("enter national code ");
        String nationalCode = getValidNationalCode();
        String username = nationalCode;
        System.out.println("enter phone number : ");
        String phoneNumber = getValidPhoneNumber();
        Location liveLocation = null;
        System.out.println("enter gender : ");
        Gender gender = Gender.valueOf(getValidGender());
        Passenger passenger = new Passenger(getPassengerAddNumber(),firstName,lastName,username,password,nationalCode,phoneNumber,liveLocation,gender);
        passengerDao.save(passenger);
        plusPassengerAddNumber();
        return passenger;
    }

    private String getValidLicencePlate() {
        boolean isContinue = true;
        String licencePlate  = null;
        while (isContinue) {
            licencePlate  = scanner.next();
            try {
                if (isValidCarLicence (licencePlate )) {
                    isContinue = false;
                } else {
                    throw new Exception("wrong Licence Plate :   \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return licencePlate ;
    }

    private boolean isValidCarLicence(String licencePlate) {
        if ( licencePlate.matches("[0-9]"+"[A-Z]"))
            return true;
        else
            return false ;
    }

    private String getValidModel() {
        boolean isContinue = true;
        String model = null;
        while (isContinue) {
            model = scanner.next();
            try {
                if (isValidCarModel(model)) {
                    isContinue = false;
                } else {
                    throw new Exception("wrong model car   \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return model;
    }

    private boolean isValidCarModel(String model) {
        if (model.equals("PEUGEOT206")||model.equals("PRIDE")||model.equals("PEUGEOT405")||model.equals("TIBA"))
            return true;
        else
            return false;
    }

    private String getValidColor() {
        boolean isContinue = true;
        String color = null;
        while (isContinue) {
            color = scanner.next();
            try {
                if (isValidColor(color)) {
                    isContinue = false;
                } else {
                    throw new Exception("wrong color  \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return color;

    }

    private boolean isValidColor(String color) {
        if (color.equals("RED")||color.equals("WHITE")||color.equals("BLUE")||color.equals("BLACK")||color.equals("BLACK")||color.equals("SILVER")||
                color.equals("GRAY")||color.equals("BEIGE"))
            return true;
        else 
            return false;
    }

    private String getValidTypeOFVehicl() {
        boolean isContinue = true;
        String typeOfVehicle = null;
        while (isContinue) {
            typeOfVehicle = scanner.next();
            try {
                if (isValidTypeOfVehicle(typeOfVehicle)) {
                    isContinue = false;
                } else {
                    throw new Exception("wrong type or write with large words   \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return typeOfVehicle;

    }

    private boolean isValidTypeOfVehicle(String typeOfVehicle) {
        if (typeOfVehicle.equals("MOTORCYCLE")||typeOfVehicle.equals("SMALLVAN")|| typeOfVehicle.equals("VAN")||typeOfVehicle.equals("CAR"))
            return true;
        else
            return false;

    }

    private String getValidGender() {
        boolean isContinue = true;
        String gender = null;
        while (isContinue) {
            gender = scanner.next();
            try {
                if (isValidGender(gender)) {
                    isContinue = false;
                } else {
                    throw new Exception("gender  \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return gender;
        
    }

    private boolean isValidGender(String gender) {
        if (gender.equals("man")||gender.equals("femal"))
            return true;
        else
            return false; 
        
    }

    private String getValidPhoneNumber() {
        boolean isContinue = true;
        String phoneNumber = null;
        while (isContinue) {
            phoneNumber = scanner.next();
            try {
                if (isValidPhoneNumber(phoneNumber)) {
                    isContinue = false;
                } else {
                    throw new Exception("wrong phone number  \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return phoneNumber;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[0-9]") && phoneNumber.length()==11 && phoneNumber.startsWith("09"))
            return true;
        else
            return false;
    }

    private String getValidNationalCode() {
        boolean isContinue = true;
        String nationalCode = null;
        while (isContinue) {
            nationalCode = scanner.next();
            try {
                if (isValidUserNationalCode(nationalCode)) {
                    isContinue = false;
                } else {
                    throw new Exception("wrong national code \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return nationalCode;
    }

    private boolean isValidUserNationalCode(String nationalCode) {
        if (nationalCode.matches("[0-9]") && nationalCode.length() == 10)
            return true;
        else
            return false;
    }

    private String getValidPassword() {
        boolean isContinue = true;
        String name = null;
        while (isContinue) {
            name = scanner.next();
            try {
                if (isValidUserNamePassword(name)) {
                    isContinue = false;
                } else {
                    throw new Exception("password must include number and large and small words \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

    private boolean isValidUserNamePassword(String name) {
        if (name.matches("[a-z]" + "[A-Z]" + "[0-9]") && name.length() > 3)
            return true;
        else
            return false;
    }

    private String getValidName() {
        boolean isContinue = true;
        String name = null;
        while (isContinue) {
            name = scanner.next();
            try {
                if (isValidName(name)) {
                    isContinue = false;
                } else {
                    throw new Exception("wrong name \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

    private boolean isValidName(String name) {
        boolean isValid = false;
        if (name.matches("[a-zA-Z]") && name.length() > 2)
               isValid=true;
        
            return isValid;
    }


}

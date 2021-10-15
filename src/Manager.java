import controlValidValue.ControlValidValue;
import dataBaseAccess.*;
import entity.*;
import entity.Preson.Driver;
import entity.Preson.Passenger;
import entity.enums.*;
import exceptions.ExceptionInput;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager<list> {
    private DriverDataBaseAccess driverDao = new DriverDataBaseAccess();
    private PassengerDataBaseAccess passengerDao = new PassengerDataBaseAccess();
    private VehicleDataBaseAccess vehicleDao = new VehicleDataBaseAccess();
    private LocationDataBaseAccess locationDao = new LocationDataBaseAccess();
    private TripDataBaseAccess tripDao = new TripDataBaseAccess();
    private ControlValidValue controlValidValue = new ControlValidValue();
    static Scanner scanner = new Scanner(System.in);

    public Manager() throws SQLException, ClassNotFoundException {
    }

    public DriverDataBaseAccess getDriverDao() {
        return driverDao;
    }

    public PassengerDataBaseAccess getPassengerDao() {
        return passengerDao;
    }

    public void addDriver(Driver driver, int vehicleId, int locationId) throws SQLException {
        int count = driverDao.save(driver, vehicleId, locationId);
        if (count == 1) {
            System.out.println("Driver : " + driver + "saved!");
        } else System.out.println("save fail !");
    }

    public void addPassenger(Passenger passenger, int locationId) throws SQLException {
        int count = passengerDao.save(passenger, locationId);
        if (count == 1) {
            System.out.println("Passenger : " + passenger + "saved!");
        } else System.out.println("save fail !");
    }

    public int driverLogin(String nationalCode, String password) throws SQLException {
        Driver driverLogin = driverDao.findDriverByNationalCode(nationalCode);
        if (driverLogin != null) {
            if (driverLogin.checkingPassword(nationalCode, password)) {
                {
                    System.out.println(" Hi Dear  " + driverLogin.getFirstName() + " " + driverLogin.getLastName());
                    if (driverLogin.getTripStatusDriver().equals(TripStatusDriver.ON_TRIP))
                        return 1;
                    else if (driverLogin.getTripStatusDriver().equals(TripStatusDriver.WAIT_FOR_TRIP))
                        return 2;
                }
            } else System.out.println("wrong username or password ");
        } else {
            Driver newDriver = singUpDriver();
            driverLogin(newDriver.getNationalCode(), newDriver.getPassword());
        }
        return -1;
    }

    public void seccondMenuForDriverLogIn(int type, String nationalCode) throws SQLException {
        switch (type) {
            case 1: {
                Trip trip = tripDao.findByDriverNationalCode(nationalCode);
                System.out.println(trip.getTripStatus() + "\n" + trip);
                System.out.println("1: confirm receipt of cash \n2:finish trip \n 3:exit \n ");
                int newType = scanner.nextInt();
                switch (newType) {
                    case 1: {
                        trip.setPaymentStatus(PaymentStatus.PAID);
                        int tripId = tripDao.findtripId(trip);
                        if (tripDao.updatePaymentStatus(PaymentStatus.PAID, tripId))
                            System.out.println("done ");
                        else System.out.println("something wrong ");
                        break;
                    }
                    case 2: {
                        trip.setTripStatus(TripStatus.FINISH);
                        int tripId = tripDao.findtripId(trip);
                        if (tripDao.updateTripStatus(TripStatus.FINISH, tripId))
                            System.out.println("done ");
                        else System.out.println("something wrong ");
                        break;

                    }
                    default: {
                        //todo
                    }
                }
            }
            case 2: {
                System.out.println("you are waiting for trip");

            }
            case 3: {
                System.out.println(" you are in inactive mode ");
            }
            default:
                System.out.println("wrong number ");
        }


    }

    public Driver singUpDriver() throws SQLException {
        System.out.println("enter first name : ");
        String firstName = controlValidValue.getValidName();
        System.out.println("enter last name : ");
        String lastName = controlValidValue.getValidName();
        System.out.println("enter password : just number ! ");
        String password = controlValidValue.getValidPassword();
        System.out.println("enter national code ");
        String nationalCode = controlValidValue.getValidNationalCode();
        String username = nationalCode;
        System.out.println("enter phone number : ");
        String phoneNumber = controlValidValue.getValidPhoneNumber();
        System.out.println("enter your coordinates location ");
        System.out.println("width: ");
        String width = controlValidValue.getValidCoordinates();
        System.out.println("length: ");
        String length = controlValidValue.getValidCoordinates();
        Location location = new Location(width, length);
        System.out.println("enter gender :\nFEMALE ,MEN ");
        Gender gender = Gender.valueOf(controlValidValue.getValidGender());
        System.out.println("enter type of vehicle :\nMOTORCYCLE , SMALLVAN , VAN , CAR ");
        TypeOfVehicle typeOfVehicle = TypeOfVehicle.valueOf(controlValidValue.getValidTypeOFVehicl().toUpperCase());
        System.out.println("enter color : \nRED , WHITE ,BLUE ,BLACK , SILVER ,GRAY ,BEIGE ");
        Color color = Color.valueOf(controlValidValue.getValidColor().toUpperCase());
        CarModel carModel;
        if (typeOfVehicle.getAbbr() == 4) {
            System.out.println("enter car model :\nPEUGEOT206 , PRIDE , PEUGEOT405 , TIBA ");
            carModel = CarModel.valueOf(controlValidValue.getValidModel().toUpperCase());
        } else {
            carModel = CarModel.valueOf("NULL");
        }
        System.out.println(" enter Licence Plate :");
        String licencePlate = controlValidValue.getValidLicencePlate();
        System.out.println("do want have trip After sign up ? y/n ");
        char select = controlValidValue.getValidChar();
        TripStatusDriver tripStatusDriver;
        if (select == 'y') {
            tripStatusDriver = TripStatusDriver.valueOf("WAIT_FOR_TRIP");
        } else {
            tripStatusDriver = TripStatusDriver.valueOf("INACTIVE");
        }
        Vehicle vehicleDriver = new Vehicle(licencePlate, typeOfVehicle, carModel, color);
        Driver driver = new Driver(firstName, lastName, username, password, nationalCode, phoneNumber, location, gender, vehicleDriver, tripStatusDriver);
        vehicleDao.save(vehicleDriver);
        locationDao.save(location);
        int locationId = locationDao.FindLocationId(location);
        int vehicleID = vehicleDao.findVehicleID(vehicleDriver);
        addDriver(driver, vehicleID, locationId);
        return driver;
    }

    public int passengerLogin(String nationalCode, String password) throws SQLException {
        Passenger passengerLogin = passengerDao.findPassengerByNationalCode(nationalCode);
        int type = -1;
        if (passengerLogin != null) {
            if (passengerLogin.checkingPassword(nationalCode, password)) {
                System.out.println(" Hi Dear  " + passengerLogin.getFirstName() + " " + passengerLogin.getLastName());
                if (passengerLogin.getTripStatus().equals(TripStatusPassenger.ON_TRIP)) {
                    type = 1;
                } else if (passengerLogin.getTripStatus().equals(TripStatusPassenger.ASK_TRIP))
                    type = 2;
            } else {
                System.out.println("wrong username or password ");
            }
        } else {
            Passenger newPassenger = signUpPassenger();
            driverLogin(newPassenger.getNationalCode(), newPassenger.getPassword());
        }
        return type;
    }

    public void seccondPassengerLogin(int type, String nationalCode) throws SQLException {
        Passenger passenger = passengerDao.findPassengerByNationalCode(nationalCode);
        int passengerId = passengerDao.findPassengerId(passenger);
        System.out.println(passenger);


        switch (type) {
            case 1: {
                System.out.println("you are on trip ");
                Trip trip = tripDao.findByPassengerNationalCode(nationalCode);
                System.out.println("trip");
                break;
            }
            case 2: {
                System.out.println("1:ask trip with cash payment \n2:ask trip payment from wallet \n3:increase wallet inventory ");
                int newType = scanner.nextInt();
                switch (newType) {
                    case 1: {
                        boolean createTrip = craeteTrip(passenger);
                        if (createTrip) {
                            Trip trip = tripDao.findByPassengerNationalCode(nationalCode);
                            int tripId = tripDao.findtripId(trip);
                            trip.setPaymentStatus(PaymentStatus.PAID);
                            if (tripDao.updatePaymentStatus(PaymentStatus.PAID, tripId))
                                System.out.println("done");
                        } else System.out.println("your request not run . ");
                        break;
                    }
                    case 2: {
                        boolean createTrip = craeteTrip(passenger);
                        if (createTrip) {
                            Trip trip = tripDao.findByPassengerNationalCode(nationalCode);
                            int tripId = tripDao.findtripId(trip);
                            if (trip.getCost() < passenger.getWallet()) {
                                if (tripDao.updatePaymentStatus(PaymentStatus.PAID, tripId)) {
                                    passenger.setWallet(passenger.getWallet() - trip.getCost());
                                    if (passengerDao.updateWalletInventory(passengerId, passenger.getWallet()))
                                        System.out.println("done\n wallet :" + passenger.getWallet());
                                } else System.out.println(" wallet inventory is not enough");
                            }
                        } else System.out.println("your request not run . ");
                        break;
                    }
                    case 3: {
                        System.out.println("enter value : ");
                        double plusInventory = scanner.nextDouble();
                        passenger.setWallet(passenger.getWallet() + plusInventory);

                        if (passengerDao.updateWalletInventory(passengerId, passenger.getWallet()))
                            System.out.println("done\n wallet :" + passenger.getWallet());
                        else System.out.println("something wrong ");
                        break;
                    }

                    default: {
                        //todo;
                    }
                }
            }
            default:
                System.out.println("wrong number");
        }

    }

    public Passenger signUpPassenger() throws SQLException {
        System.out.println("enter first name : ");
        String firstName = controlValidValue.getValidName();
        System.out.println("enter last name : ");
        String lastName = controlValidValue.getValidName();
        System.out.println("enter password : just number !");
        String password = controlValidValue.getValidPassword();
        System.out.println("enter national code ");
        String nationalCode = controlValidValue.getValidNationalCode();
        String username = nationalCode;
        System.out.println("enter phone number : ");
        String phoneNumber = controlValidValue.getValidPhoneNumber();
        System.out.println("enter gender\nFEMALE ,MEN : ");
        Gender gender = Gender.valueOf(controlValidValue.getValidGender().toUpperCase());
        System.out.println("enter your coordinates location ");
        System.out.println("width: ");
        String width = controlValidValue.getValidCoordinates();
        System.out.println("length: ");
        String length = controlValidValue.getValidCoordinates();
        Location location = new Location(width, length);
        locationDao.save(location);
        int locationId = locationDao.FindLocationId(location);
        Passenger passenger = new Passenger(firstName, lastName, username, password, nationalCode, phoneNumber, location, gender, 0, TripStatusPassenger.NOT_ON_TRIP);
        addPassenger(passenger, locationId);
        return passenger;
    }

    public double calculateDistance(Location origin, Location destination) {
        //(x2-x1)^2 = widthDifferencePow2
        double widthDifferencePow2 = Math.pow(Double.parseDouble(destination.getWidth()) - Double.parseDouble(origin.getWidth()), 2);
        //(y2-y1)^2 = LengthDifferencePow2
        double lengthDifferencePow2 = Math.pow(Double.parseDouble(destination.getLength()) - Double.parseDouble(origin.getLength()), 2);
        double distance = Math.sqrt(widthDifferencePow2 + lengthDifferencePow2);
        return distance;
    }

    public Driver findNearestDriver(Location origin) throws SQLException {

        List<Driver> driverWaitForTrip = driverDao.FindDriverWaitForTrip();
        Driver nearestDriver = new Driver();
        double minDistance = calculateDistance(driverWaitForTrip.get(0).getLiveLocation(), origin);
        for (Driver driver : driverWaitForTrip) {
            if (calculateDistance(driver.getLiveLocation(), origin) <= minDistance)
                minDistance = calculateDistance(driver.getLiveLocation(), origin);
            nearestDriver = driver;
        }
        return nearestDriver;

    }

    public double caluteCost(Location origin, Location destination) {
        return 1000 * calculateDistance(origin, destination);
    }

    public boolean craeteTrip(Passenger passenger) throws SQLException {

        System.out.println("enter location of destination :\n width : ");
        String width = controlValidValue.getValidCoordinates();
        System.out.println("length : ");
        String length = controlValidValue.getValidCoordinates();
        Location destination = new Location(width, length);
        Location origin = passenger.getLiveLocation();
        double cost = caluteCost(passenger.getLiveLocation(), destination);
        System.out.println("cost : " + cost + "\n Confirm trip : Y/N?");
        char confirm = controlValidValue.getValidChar();
        if (confirm == 'y') {
            Driver driver = findNearestDriver(passenger.getLiveLocation());
            Trip trip = new Trip(driver, passenger, cost, passenger.getLiveLocation(), destination);
            locationDao.save(destination);
            int driverId = driverDao.findIdDriverByNationalCode(driver.getNationalCode());
            int passengerId = passengerDao.findPassengerId(passenger);
            if (tripDao.save(trip) == 1) {
                if (driverDao.updateTripStatus(TripStatusDriver.ON_TRIP, driverId) && passengerDao.updateTripStatus(TripStatusPassenger.ON_TRIP, passengerId)) {
                    System.out.println("new trip " + trip + "is started ");
                    return true;
                } else System.out.println("something wrong with updating status ");
            } else System.out.println("something wrong ");
            return false;
        }
        else return false;

    }
}



package entity.Preson;

import entity.Location;
import entity.enums.Gender;

public class Person {
    private String firstName ;
    private String lastName ;
    private String userName ;
    private String password ;
    private String nationalCode ;
    private String phoneNumber ;
    private Location liveLocation;
    private Gender gender;
    private int id ;

    public Person(int id , String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Location liveLocation, Gender gender) {
        this.id = id ;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.nationalCode = nationalCode;

        this.phoneNumber = phoneNumber;
        this.liveLocation = liveLocation;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }


    public String getPassword() {
        return password;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public Location getLiveLocation() {
        return liveLocation;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setLiveLocation(Location liveLocation) {
        this.liveLocation = liveLocation;
    }

    public boolean checkingPassword(String userName , String password ){
        if (userName ==getUserName()){
            if (password== getPassword()){
                return true ;
            };
        }

        return false ;
    }
}

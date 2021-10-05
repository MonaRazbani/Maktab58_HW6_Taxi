package entity.Preson;

import entity.Location;
import entity.enums.Gender;

public class Person {
    private String firstName ;
    private String lastName ;
    private String userName ;
    private String password ;
    private String phoneNumber ;
    private Location liveLocation;
    private Gender gender;

    public Person(String firstName, String lastName, String userName, String password, String phoneNumber,Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender= gender;

    }

    public String getFirstName() {
        return firstName;
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

    public void setLiveLocation(Location liveLocation) {
        this.liveLocation = liveLocation;
    }

    public boolean checkingPassword(String userName , String password ){
        if (userName ==getUserName()){
            if (password== getPassword()){
                System.out.println("done");
                return true ;
            };
        }
        else System.out.println("wrong username or password ");
        return false ;
    }
}

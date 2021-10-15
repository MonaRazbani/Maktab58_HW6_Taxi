package controlValidValue;

import exceptions.ExceptionInput;

import java.util.Scanner;

public class ControlValidValue {
    static private Scanner scanner = new Scanner(System.in);
    public char getValidChar() {
        boolean isContinue = true;
        while (isContinue) {
            char select = scanner.next().charAt(0);
            try {
                if (isValidChar(select)) {
                    isContinue = false;
                    return select;
                } else {
                    throw new ExceptionInput("wrong model car   \n try again ");
                }

            }
            catch (ExceptionInput e) {
                System.out.println(e.getMessage());
            }
        }
        return 'e';
    }

    public boolean isValidChar(char select) {

        if (select == 'y'||select == 'n'){
            return true;
        }
        else return false;

    }

    public String getValidCoordinates() {
        boolean isContinue = true;
        String coordinates = null;
        while (isContinue) {
            coordinates = scanner.next();
            try {
                if (isValidCoordinates(coordinates)) {
                    isContinue = false;
                } else {
                    throw new Exception("wrong model car   \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return coordinates;
    }

    public boolean isValidCoordinates(String coordinates) {
        if (coordinates.matches("[0-9]*"))
            return true;
        else
            return false;
    }

    public String getValidLicencePlate() {
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

    public boolean isValidCarLicence(String licencePlate) {
        boolean isValid = false;
        if ( licencePlate.matches("[0-9]*"))
            // if(licencePlate.matches("[a-z]*"))
            isValid =true ;
        return isValid;
    }

    public String getValidModel() {
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

    public boolean isValidCarModel(String model) {
        if (model.toUpperCase().equals("PEUGEOT206")||model.toUpperCase().equals("PRIDE")||model.toUpperCase().equals("PEUGEOT405")||model.toUpperCase().equals("TIBA"))
            return true;
        else
            return false;
    }

    public String getValidColor() {
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

    public boolean isValidColor(String color) {
        if (color.equals("RED")||color.equals("WHITE")||color.equals("BLUE")||color.equals("BLACK")||color.equals("BLACK")||color.equals("SILVER")||
                color.equals("GRAY")||color.equals("BEIGE"))
            return true;
        else
            return false;
    }

    public String getValidTypeOFVehicl() {
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

    public boolean isValidTypeOfVehicle(String typeOfVehicle) {
        if (typeOfVehicle.equals("MOTORCYCLE")||typeOfVehicle.equals("SMALLVAN")|| typeOfVehicle.equals("VAN")||typeOfVehicle.equals("CAR"))
            return true;
        else
            return false;

    }

    public String getValidGender() {
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

    public boolean isValidGender(String gender) {
        boolean isvalid = false;
        if (gender.toUpperCase().equals("MEN"))
            isvalid = true ;
        else if (gender.toUpperCase().equals("FEMALE"))
            isvalid = true ;
        return isvalid;

    }

    public String getValidPhoneNumber() {
        boolean isContinue = true;
        String phoneNumber = null;
        outer: while (isContinue) {
            phoneNumber = scanner.next();
            try {
                if (isValidPhoneNumber(phoneNumber))
                    isContinue= false;
                else
                    throw new Exception("wrong phone number  \n try again ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return phoneNumber;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        boolean isValid=false;
        if (phoneNumber.matches("[0-9]*"))
            if ( phoneNumber.startsWith("09"))
                if (phoneNumber.length()==11)
                    isValid= true;
        return isValid;
    }

    public String getValidNationalCode() {
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

    public boolean isValidUserNationalCode(String nationalCode) {
        boolean isVAlid = false;
        if (nationalCode.matches("[0-9]*") )
            if ( nationalCode.length() == 10)
                isVAlid=true;
        return isVAlid;
    }

    public String getValidPassword() {
        boolean isContinue = true;
        String name = null;
        while (isContinue) {
            name = scanner.next();
            try {
                if (isValidUserNamePassword(name)) {
                    isContinue = false;
                } else {
                    throw new Exception("password must include number \n try again ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

    public boolean isValidUserNamePassword(String name) {
        if (name.matches("[0-9]*") && name.length() > 3)
            return true;
        else
            return false;
    }

    public String getValidName() {
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

    public boolean isValidName(String name) {

        if (name.matches("[a-zA-Z]*") && name.length() > 2)
            return true;
        else
            return false;
    }

}

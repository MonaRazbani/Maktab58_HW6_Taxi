import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Manager manager= new Manager();
        outer :while (true) {
        System.out.println("enter your action : \n" +
                "1.Add a group of drivers \n" +
                "2.Add a group of passengers \n" +
                "3.Driver signup or login \n" +
                "4.Passenger signup or login \n" +
                "5.Show a list of drivers \n" +
                "6.Show a list of passengers\n"+
                "7.new trip :");
        Scanner scanner = new Scanner(System.in);
            int type = scanner.nextInt();
            switch (type) {
                case 1: {
                    System.out.println("enter num of driver : ");
                    int numOfInsert = scanner.nextInt();
                    for (int i = 0; i < numOfInsert; i++) {
                        manager.singUpDriver();
                    }
                    break;
                }
                case 2: {
                    System.out.println("enter num of passenger : ");
                    int numOfInsert = scanner.nextInt();
                    for (int i = 0; i < numOfInsert; i++)
                        manager.signUpPassenger();

                    break;
                }
                case 3: {
                    System.out.println("enter your username /national code : ");
                    String nationalCode = scanner.next();
                    System.out.println("enter your password : ");
                    String password = scanner.next();
                    int typeNewMenu = manager.driverLogin(nationalCode, password);
                    manager.seccondMenuForDriverLogIn(typeNewMenu,nationalCode);
                    break ;
                }
                case 4: {
                    System.out.println("enter your username /national code : ");
                    String nationalCode = scanner.next();
                    System.out.println("enter your password : ");
                    String password = scanner.next();
                    int newTypeMenu = manager.passengerLogin(nationalCode, password);
                    manager.seccondPassengerLogin(newTypeMenu,nationalCode);
                    break ;
                }
                case 5: {
                    System.out.println(manager.getDriverDao().display());
                }

                case 6: {

                    System.out.println(manager.getPassengerDao().display());
                }
                default: {
                    break outer;
                }
            }
        }
    }
}

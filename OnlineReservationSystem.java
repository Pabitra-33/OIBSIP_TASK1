import java.util.HashMap; //this package is used to provide the basic implementation of the Map interface of Java
import java.util.Map; //store the data in key, value pairs where a key is an object
import java.util.Scanner; //used to take user input through scanner class

class User {
    private String username;
    private String password;

    public User(String username, String password) { //Constructor
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class ReservationSystem { 
    private Map<String, String> reservations;

    public ReservationSystem() {
        reservations = new HashMap<>();
    }

    public boolean makeReservation(String username, String reservationDetails) {
        if (!reservations.containsKey(username)) {
            reservations.put(username, reservationDetails);
            return true;
        }
        return false;
    }

    public boolean cancelReservation(String username) {
        if (reservations.containsKey(username)) {
            reservations.remove(username);
            return true;
        }
        return false;
    }

    public void displayReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("Reservations:");
            for (Map.Entry<String, String> entry : reservations.entrySet()) {
                System.out.println("Username: " + entry.getKey() + ", Reservation: " + entry.getValue());
            }
        }
    }
}

public class OnlineReservationSystem {
    private static Map<String, User> users;
    private static ReservationSystem reservationSystem;

    public static void main(String[] args) {
        users = new HashMap<>();
        reservationSystem = new ReservationSystem();

        // Sample users
        users.put("user1", new User("user1", "password1"));
        users.put("user2", new User("user2", "password2"));

        Scanner scanner = new Scanner(System.in);//to take input from user

        while (true) {
            System.out.println("=================== Welcome To Online Reservation System =================");
            System.out.println("1. Login");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); //store the input in choice variable

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.next();//taking username input from user
        System.out.print("Password: ");
        String password = scanner.next(); //taking password input from user

        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                System.out.println("Login successful!");

                while (true) {
                    System.out.println("1. Make a reservation");
                    System.out.println("2. Cancel reservation");
                    System.out.println("3. Display reservations");
                    System.out.println("4. Logout");

                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            makeReservation(username, scanner);
                            break;
                        case 2:
                            cancelReservation(username);
                            break;
                        case 3:
                            displayReservations();
                            break;
                        case 4:
                            System.out.println("Logging out...");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid password. Please try again.");
            }
        } else {
            System.out.println("User not found. Please try again.");
        }
    }

    private static void makeReservation(String username, Scanner scanner) {
        scanner.nextLine(); // Clear the input buffer

        System.out.print("Enter reservation details: ");
        String reservationDetails = scanner.nextLine();

        if (reservationSystem.makeReservation(username, reservationDetails)) {
            System.out.println("Reservation created successfully.");
        } else {
            System.out.println("Reservation already exists for this user."); //if this user have a reservation then it shows reservation already exist
        }
    }

    private static void cancelReservation(String username) {
        if (reservationSystem.cancelReservation(username)) {
            System.out.println("Reservation canceled successfully.");
        } else {
            System.out.println("No reservation found for this user.");
        }
    }

    private static void displayReservations() {
        reservationSystem.displayReservations();
    }
}
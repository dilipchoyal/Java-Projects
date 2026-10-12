import java.util.Scanner;

public class CarRentalFleet {
    static Scanner sc = new Scanner(System.in);
    static Car[] cars = {
        new Car(1, "Toyota", 2500),
        new Car(2, "Honda", 2800),
        new Car(3, "Hyundai", 2200)
    };

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: addReservation(); break;
                case 2: checkIn(); break;
                case 3: checkOut(); break;
                case 4: viewVehicles(); break;
                case 5: maintenance(); break;
                case 6: billingReport(); break;
                case 7: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice");
            }
        } while (choice != 7);
    }

    private static void printMenu() {
        System.out.println("\nFleet Management System");
        System.out.println("1. Add Reservation");
        System.out.println("2. Check In");
        System.out.println("3. Check Out");
        System.out.println("4. View Vehicles");
        System.out.println("5. Maintenance");
        System.out.println("6. Billing Report");
        System.out.println("7. Exit");
    }

    static void addReservation() {
        viewVehicles();
        System.out.print("Enter car ID: ");
        Car car = findCar(sc.nextInt());
        sc.nextLine();
        if (car == null || !car.status.equals("Available")) {
            System.out.println("Car not available");
            return;
        }
        System.out.print("Customer name: ");
        car.customer = sc.nextLine();
        System.out.print("Number of days: ");
        car.days = sc.nextInt();
        car.status = "Reserved";
        System.out.println("Reserved. Total: Rs. " + car.total());
    }

    static void checkIn() {
        System.out.print("Enter car ID: ");
        Car car = findCar(sc.nextInt());
        if (car == null || !car.status.equals("Rented"))
            System.out.println("Car is not rented");
        else {
            System.out.println("Final bill: Rs. " + car.total());
            car.status = "Available";
            car.customer = "";
            car.days = 0;
        }
    }

    static void checkOut() {
        System.out.print("Enter car ID: ");
        Car car = findCar(sc.nextInt());
        if (car == null || !car.status.equals("Reserved"))
            System.out.println("No reservation for this car");
        else {
            car.status = "Rented";
            System.out.println("Car handed to " + car.customer);
        }
    }

    static void viewVehicles() {
        for (Car car : cars) System.out.println(car);
    }

    static void maintenance() {
        System.out.println("Toyota - Oil Change\nHonda - Brake Check\nHyundai - Tyre Service");
    }

    static void billingReport() {
        int total = 0;
        for (Car car : cars) if (!car.status.equals("Available")) {
            System.out.println(car.customer + " - " + car.model + " - Rs. " + car.total());
            total += car.total();
        }
        System.out.println("Total: Rs. " + total);
    }

    static Car findCar(int id) {
        for (Car car : cars) if (car.id == id) return car;
        return null;
    }
}

class Car {
    int id, price, days;
    String model, customer = "", status = "Available";

    Car(int id, String model, int price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    int total() { return price * days; }

    public String toString() {
        return id + " | " + model + " | Rs." + price + "/day | " + status;
    }
}
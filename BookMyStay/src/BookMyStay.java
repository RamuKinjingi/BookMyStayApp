

// Main Class (Your Required Structure)
public class BookMyStay {
    public static void main(String[] args) {

        // Polymorphism
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailability = 10;
        int doubleAvailability = 5;
        int suiteAvailability = 2;

        System.out.println("=== Hotel Room Details ===\n");

        single.displayDetails();
        System.out.println("Available: " + singleAvailability + "\n");

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailability + "\n");

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailability + "\n");

        System.out.println("=== End of Program ===");
    }
}
import java.util.HashMap;
import java.util.Map;

public class BookMyStay {
    public static void main(String[] args) {

        // Room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Inventory initialization
        RoomInventory inventory = new RoomInventory();

        // Display room details
        System.out.println("=== Room Details ===\n");

        single.displayDetails();
        System.out.println();

        doubleRoom.displayDetails();
        System.out.println();

        suite.displayDetails();
        System.out.println();

        // Display inventory
        inventory.displayInventory();

        // Update example
        System.out.println("Updating Single Room availability to 8...\n");
        inventory.updateAvailability("Single Room", 8);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("=== End of Program ===");
    }
}
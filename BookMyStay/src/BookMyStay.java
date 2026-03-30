
public class BookMystay {
    public static void main(String args[]){


        // Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        // Register room types
        inventory.addRoomType("Single", 10);
        inventory.addRoomType("Double", 5);
        inventory.addRoomType("Suite", 2);

        // Display initial state
        inventory.displayInventory();

        // Simulate booking (reduce availability)
        System.out.println("\nBooking 2 Single rooms...");
        inventory.updateAvailability("Single", -2);

        // Simulate cancellation (increase availability)
        System.out.println("Cancelling 1 Double room...");
        inventory.updateAvailability("Double", +1);

        // Check specific availability
        System.out.println("\nAvailable Suites: " + inventory.getAvailability("Suite"));

        // Final inventory state
        inventory.displayInventory();
    }
}
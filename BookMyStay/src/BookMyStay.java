import java.util.*;

// Booking Request class
class BookingRequest {
    String customerName;
    String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

public class BookMyStay{

    // FIFO Queue for booking requests
    private static Queue<BookingRequest> requestQueue = new LinkedList<>();

    // Inventory: roomType -> available count
    private static Map<String, Integer> inventory = new HashMap<>();

    // Allocated rooms: roomType -> set of room IDs
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Global set to ensure unique room IDs
    private static Set<String> usedRoomIds = new HashSet<>();

    // Room ID counter
    private static int roomCounter = 1;

    public static void main(String[] args) {

        // Initialize inventory
        inventory.put("DELUXE", 2);
        inventory.put("SUITE", 1);
        inventory.put("STANDARD", 2);

        // Add booking requests to queue
        requestQueue.add(new BookingRequest("Avinash", "DELUXE"));
        requestQueue.add(new BookingRequest("Ravi", "SUITE"));
        requestQueue.add(new BookingRequest("Kiran", "DELUXE"));
        requestQueue.add(new BookingRequest("Sneha", "STANDARD"));
        requestQueue.add(new BookingRequest("John", "SUITE")); // should fail

        // Process all requests
        processBookings();

        // Display final allocation
        displayAllocations();
    }

    // Process bookings in FIFO order
    private static void processBookings() {
        while (!requestQueue.isEmpty()) {
            BookingRequest request = requestQueue.poll();
            confirmReservation(request);
        }
    }

    // Confirm reservation and allocate room
    private static void confirmReservation(BookingRequest request) {
        String roomType = request.roomType;

        // Check availability
        if (!inventory.containsKey(roomType) || inventory.get(roomType) <= 0) {
            System.out.println("Booking FAILED for " + request.customerName +
                    " (No " + roomType + " rooms available)");
            return;
        }

        // Generate unique room ID
        String roomId;
        do {
            roomId = roomType.substring(0, 3).toUpperCase() + "-" + roomCounter++;
        } while (usedRoomIds.contains(roomId));

        // Atomic allocation
        usedRoomIds.add(roomId);

        allocatedRooms.putIfAbsent(roomType, new HashSet<>());
        allocatedRooms.get(roomType).add(roomId);

        // Update inventory immediately
        inventory.put(roomType, inventory.get(roomType) - 1);

        // Confirm booking
        System.out.println("Booking CONFIRMED for " + request.customerName +
                " | Room Type: " + roomType +
                " | Room ID: " + roomId);
    }

    // Display final allocations
    private static void displayAllocations() {
        System.out.println("\n--- Final Room Allocations ---");
        for (String type : allocatedRooms.keySet()) {
            System.out.println(type + " -> " + allocatedRooms.get(type));
        }

        System.out.println("\n--- Remaining Inventory ---");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}
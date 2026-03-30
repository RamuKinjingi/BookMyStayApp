import java.util.*;

// Service class
class Service {
    String serviceName;
    double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String toString() {
        return serviceName + " (₹" + cost + ")";
    }
}

// Booking Request class
class BookingRequest {
    String customerName;
    String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

public class BookMyStay {

    // ----------- Booking सिस्टम -----------
    private static Queue<BookingRequest> requestQueue = new LinkedList<>();
    private static Map<String, Integer> inventory = new HashMap<>();
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private static Set<String> usedRoomIds = new HashSet<>();
    private static int roomCounter = 1;

    // ----------- Services सिस्टम -----------
    private static Map<String, List<Service>> reservationServices = new HashMap<>();

    public static void main(String[] args) {

        // Inventory setup
        inventory.put("DELUXE", 2);
        inventory.put("SUITE", 1);

        // Booking requests
        requestQueue.add(new BookingRequest("Avinash", "DELUXE"));
        requestQueue.add(new BookingRequest("Ravi", "SUITE"));

        processBookings();
        displayAllocations();

        // Add services
        addService("DEL-1", new Service("Breakfast", 200));
        displayServices("DEL-1");
        calculateTotalCost("DEL-1");
    }

    // -------- Booking Methods --------
    private static void processBookings() {
        while (!requestQueue.isEmpty()) {
            confirmReservation(requestQueue.poll());
        }
    }

    private static void confirmReservation(BookingRequest request) {
        String type = request.roomType;

        if (!inventory.containsKey(type) || inventory.get(type) <= 0) {
            System.out.println("Booking FAILED for " + request.customerName);
            return;
        }

        String roomId = type.substring(0, 3).toUpperCase() + "-" + roomCounter++;

        usedRoomIds.add(roomId);
        allocatedRooms.putIfAbsent(type, new HashSet<>());
        allocatedRooms.get(type).add(roomId);
        inventory.put(type, inventory.get(type) - 1);

        System.out.println("CONFIRMED: " + request.customerName + " -> " + roomId);
    }

    private static void displayAllocations() {
        System.out.println(allocatedRooms);
    }

    // -------- Service Methods --------
    private static void addService(String id, Service s) {
        reservationServices.putIfAbsent(id, new ArrayList<>());
        reservationServices.get(id).add(s);
    }

    private static void displayServices(String id) {
        System.out.println(reservationServices.get(id));
    }

    private static void calculateTotalCost(String id) {
        double total = 0;
        for (Service s : reservationServices.getOrDefault(id, new ArrayList<>())) {
            total += s.cost;
        }
        System.out.println("Total: ₹" + total);
    }
}
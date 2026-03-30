import java.util.*;

// Actor: Reservation (Guest booking request)
class Reservation {
    private String guestName;
    private String roomType;
    private int nights;

    public Reservation(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName +
                ", Room Type: " + roomType +
                ", Nights: " + nights;
    }
}

// Actor: Booking Request Queue (FIFO handling)
class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added: " + reservation.getGuestName());
    }

    // View all pending requests
    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("No pending booking requests.");
            return;
        }

        System.out.println("\n--- Booking Request Queue (FIFO Order) ---");
        for (Reservation r : queue) {
            System.out.println(r);
        }
    }

    // Get next request (for future processing)
    public Reservation getNextRequest() {
        return queue.peek(); // does NOT remove
    }

    // Remove request after processing (future use)
    public Reservation processNextRequest() {
        return queue.poll(); // removes from queue
    }
}

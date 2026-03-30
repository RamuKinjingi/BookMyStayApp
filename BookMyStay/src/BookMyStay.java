public class BookMyStay {
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulating multiple guest requests
        Reservation r1 = new Reservation("Avinash", "Deluxe", 2);
        Reservation r2 = new Reservation("Ravi", "Suite", 3);
        Reservation r3 = new Reservation("Priya", "Standard", 1);
        Reservation r4 = new Reservation("Kiran", "Deluxe", 4);

        // Step 1: Add requests (arrival order)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);
        bookingQueue.addRequest(r4);

        // Step 2: Display queue
        bookingQueue.displayQueue();

        // Step 3: Show next request (no removal)
        System.out.println("\nNext request to process (peek):");
        System.out.println(bookingQueue.getNextRequest());

        // Step 4: Process one request (FIFO)
        System.out.println("\nProcessing request:");
        System.out.println(bookingQueue.processNextRequest());

        // Step 5: Display updated queue
        bookingQueue.displayQueue();
    }
}
import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private boolean booked;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.booked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Room No: " + roomNumber +
                " | Category: " + category +
                " | Status: " + (booked ? "Booked" : "Available");
    }
}

class Booking {
    private String customerName;
    private String phoneNumber;
    private Room room;

    public Booking(String customerName, String phoneNumber, Room room) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer: " + customerName +
                " | Phone: " + phoneNumber +
                " | Room No: " + room.getRoomNumber() +
                " | Category: " + room.getCategory();
    }
}

class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;

    public Hotel() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();

        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(103, "Standard"));

        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(203, "Deluxe"));

        rooms.add(new Room(301, "Suite"));
        rooms.add(new Room(302, "Suite"));
    }

    public void displayRooms() {
        System.out.println("\n===== ROOM LIST =====");

        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void searchByCategory(String category) {

        boolean found = false;

        System.out.println("\nAvailable " + category + " Rooms:");

        for (Room room : rooms) {

            if (room.getCategory().equalsIgnoreCase(category)
                    && !room.isBooked()) {

                System.out.println(room);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    public void bookRoom(String customerName,
                         String phoneNumber,
                         int roomNumber) {

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNumber) {

                if (!room.isBooked()) {

                    room.setBooked(true);

                    Booking booking =
                            new Booking(customerName,
                                    phoneNumber,
                                    room);

                    bookings.add(booking);

                    System.out.println("\nRoom booked successfully!");
                    System.out.println(booking);

                    return;
                } else {
                    System.out.println("Room already booked!");
                    return;
                }
            }
        }

        System.out.println("Invalid room number.");
    }

    public void cancelBooking(int roomNumber) {

        for (Booking booking : bookings) {

            if (booking.getRoom().getRoomNumber() == roomNumber) {

                booking.getRoom().setBooked(false);

                bookings.remove(booking);

                System.out.println("Booking cancelled successfully!");
                return;
            }
        }

        System.out.println("Booking not found.");
    }

    public void viewBookings() {

        System.out.println("\n===== BOOKING DETAILS =====");

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}

public class HotelReservationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Hotel hotel = new Hotel();

        while (true) {

            System.out.println("\n==============================");
            System.out.println(" HOTEL RESERVATION SYSTEM ");
            System.out.println("==============================");

            System.out.println("1. View All Rooms");
            System.out.println("2. Search Room Category");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View Bookings");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    hotel.displayRooms();
                    break;

                case 2:

                    System.out.print(
                            "Enter Category (Standard/Deluxe/Suite): ");

                    String category = sc.nextLine();

                    hotel.searchByCategory(category);

                    break;

                case 3:

                    System.out.print("Customer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Phone Number: ");
                    String phone = sc.nextLine();

                    System.out.print("Room Number: ");
                    int roomNo = sc.nextInt();

                    hotel.bookRoom(name, phone, roomNo);

                    break;

                case 4:

                    System.out.print(
                            "Enter Room Number to Cancel: ");

                    int cancelRoom = sc.nextInt();

                    hotel.cancelBooking(cancelRoom);

                    break;

                case 5:

                    hotel.viewBookings();

                    break;

                case 6:

                    System.out.println(
                            "Thank you for using the system!");

                    sc.close();
                    System.exit(0);

                default:

                    System.out.println("Invalid choice!");
            }
        }
    }
}
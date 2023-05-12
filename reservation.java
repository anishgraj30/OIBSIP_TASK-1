import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
class Reservation {
    private int pnr;
    private String name;
    private String date;
    private int numberOfGuests;

    public Reservation(int pnr, String name, String date, int numberOfGuests) {
        this.pnr = pnr;
        this.name = name;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
    }

    public int getpnr() {
        return pnr;
    }

    public String getName() {
        return name;
    }
 public String getDate() {
        return date;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}


 class ReservationSystem {
    private List<Reservation> reservations = new ArrayList<>();
    private int nextpnr = 101;

    public Reservation makeReservation(String name, String date, int numberOfGuests) {
        Reservation reservation = new Reservation(nextpnr++, name, date, numberOfGuests);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
        public Reservation getReservationById(int pnr) {
        for (Reservation reservation : reservations) {
            if (reservation.getpnr() == pnr) {
                return reservation;
            }
        }
        return null;
    }

    public boolean cancelReservation(int pnr) {
        Reservation reservation = getReservationById(pnr);
        if (reservation != null) {
            reservations.remove(reservation);
            return true;
        }
        return false;
    }
}

 class ReservationSystemUI {
    private ReservationSystem reservationSystem = new ReservationSystem();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. View all reservations");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
 switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Date: ");
                    String date = scanner.nextLine();
                    System.out.print("Number of guests: ");
                    int numberOfGuests = scanner.nextInt();
                    scanner.nextLine();

                    Reservation reservation = reservationSystem.makeReservation(name, date, numberOfGuests);
                    System.out.println("Reservation made with PNR " + reservation.getpnr());
                    break;
                case 2:
                    System.out.println("Reservations:");
                    for (Reservation r : reservationSystem.getReservations()) {
                        System.out.println(r.getpnr() + " - " + r.getName() + " - " + r.getDate() + " - " + r.getNumberOfGuests());
                    }
                    break;
                case 3:
                 System.out.print("Enter PNR to cancel: ");
                    int pnr = scanner.nextInt();
                    scanner.nextLine();

                    if (reservationSystem.cancelReservation(pnr)) {
                        System.out.println("Reservation canceled successfully");
                    } else {
                        System.out.println("Reservation not found");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
            
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        ReservationSystemUI obj = new ReservationSystemUI();
        obj.start();
    }
}
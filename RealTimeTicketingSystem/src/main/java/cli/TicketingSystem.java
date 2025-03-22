package cli;
import java.util.*;

public class TicketingSystem {

    static Scanner sc = new Scanner(System.in);
    static int noOfTickets;
    static int ticketsReleaseRate;
    static int customerRetrievalRate;
    static int maxTicketCapacity;

    public static void main(String[] args) {
        while(true) {
            try {
                System.out.println(" 1) Add configuration");
                System.out.println(" 2) Save configuration");
                System.out.println(" 3) View configuration");
                System.out.println(" 4) Exit");
                System.out.println(" 5) Start Simulation");
                System.out.print(" Enter a number: ");

                int option = sc.nextInt();


                switch (option) {
                    case 1:
                        noOfTickets = configTotalTickets();
                        ticketsReleaseRate = configTicketReleaseRate();
                        customerRetrievalRate = configCustomerRetrievalRate();
                        maxTicketCapacity = configMaxTicketCapacity();
                        break;
                    case 2:
                        saveConfiguration();
                        break;
                    case 3:
                        viewConfiguration();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    case 5:
                        startingThreading();
                        break;
                    default:
                        System.out.println("Enter a number between 1-4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                sc.nextLine();
            }
        }
    }
    public static int configTotalTickets(){
        while(true) {
            try {
                System.out.print("Enter the number of tickets: ");
                int noOfTickets = sc.nextInt();

                if (noOfTickets > 0) {
                    return noOfTickets;
                } else {
                    System.out.println("Tickets cannot be negative");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number ");
                sc.nextLine();
            }
        }
    }


    public static int configTicketReleaseRate(){
        while(true) {
            try {
                System.out.print("Enter the ticket release rate: ");
                int ticketReleaseRate = sc.nextInt();

                if (ticketReleaseRate > 0) {
                    return ticketReleaseRate;
                } else {
                    System.out.println("Release rate cannot be negative");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number ");
                sc.nextLine();
            }
        }
    }

    public static int configCustomerRetrievalRate(){
        while(true) {
            try {
                System.out.print("Enter the customer Retrieval Rate: ");
                customerRetrievalRate = sc.nextInt();

                if (customerRetrievalRate > 0) {
                    return customerRetrievalRate;
                } else {
                    System.out.println("Invalid input! Please enter a valid input.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number ");
                sc.nextLine();
            }
        }
    }

    public static int configMaxTicketCapacity(){
        while(true) {
            try {
                System.out.print("Enter the Maximum Ticket Capacity: ");
                maxTicketCapacity = sc.nextInt();

                if (maxTicketCapacity > 0) {
                    return maxTicketCapacity;
                } else {
                    System.out.println("Invalid input! Please enter a valid input.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number ");
                sc.nextLine();
            }
        }
    }

    public static void saveConfiguration(){
        Configuration save = new Configuration(noOfTickets, ticketsReleaseRate, customerRetrievalRate, maxTicketCapacity);
        save.saveConfiguration("TicketConfig.json");
    }
    public static void viewConfiguration(){
        Configuration load = new Configuration();
        load.loafConfiguration("TicketConfig.json");
    }

    public static void startingThreading(){

        String[] vendorList = {"Vendor 001", ";Vendor 002","Vendor 003","Vendor 004","Vendor 005"};
        String[] customerList = {"Customer 001","Customer 002","Customer 003","Customer 004"};

        TicketPool sharedPool = new TicketPool(maxTicketCapacity);

        List<Vendor> vendors = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        for (String vendorName: vendorList) {
            Vendor vendor = new Vendor(sharedPool,vendorName);
            vendors.add(vendor);
        }

        for (String customerName: customerList) {
            Customer customer = new Customer(sharedPool,customerName);
            customers.add(customer);
        }

        for (Vendor vendor: vendors) {
            Thread vendorNames = new Thread(vendor);
            vendorNames.start();
        }

        for (Customer customer: customers) {
            Thread customerNames = new Thread(customer);
            customerNames.start();
        }
    }
}
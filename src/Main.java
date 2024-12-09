import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int totalTickets = 0;
        int ticketReleaseRate = 0;
        int customerRetrievalRate = 0;
        int maxTicketCapacity = 0;
        boolean isConfigComplete = false;
        String currentField = "totalTickets";

        Scanner scan = new Scanner(System.in);
        System.out.println("System started");
        System.out.println("Start setting configuration: ");
        while (!isConfigComplete) {
            try {
                switch (currentField) {
                    case "totalTickets":
                        System.out.println("Value for total tickets: ");
                        totalTickets = scan.nextInt();
                        if (totalTickets < 0 || totalTickets > 100) {
                            System.out.println("Please provide a value between 0 and 100");
                        } else {
                            currentField = "ticketReleaseRate";
                            break;
                        }
                    case "ticketReleaseRate":
                        System.out.println("Value for ticket release rate: ");
                        ticketReleaseRate = scan.nextInt();
                        if (ticketReleaseRate < 0 || ticketReleaseRate > 10000) {
                            System.out.println("Please provide a value between 0 and 10000");
                        } else {
                            currentField = "customerRetrievalRate";
                            break;
                        }
                    case "customerRetrievalRate":
                        System.out.println("Value for customer retrieval rate: ");
                        customerRetrievalRate = scan.nextInt();
                        if (customerRetrievalRate < 0 || customerRetrievalRate > 10000) {
                            System.out.println("Please provide a value between 0 and 10000");
                        } else {
                            currentField = "maxTicketCapacity";
                            break;
                        }
                    case "maxTicketCapacity":
                        System.out.println("Value for max ticket capacity: ");
                        maxTicketCapacity = scan.nextInt();
                        if (maxTicketCapacity < 0 || maxTicketCapacity > 100) {
                            System.out.println("Please provide a value between 0 and 100");
                        } else {
                            isConfigComplete = true;
                            break;
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please provide a integer value");
            }
        }
        System.out.println("Configuration started");
        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        Ticketpool ticketpool = new Ticketpool(maxTicketCapacity, ticketReleaseRate);

        //add vendors
        for (int i = 0; i < 5; i++) {
            Vendor newVendor = new Vendor(i, ticketpool);
            newVendor.startAddingTicket();
        }

        //add customers
        for (int i = 0; i < 5; i++) {
            Customer newCustomer = new Customer(ticketpool, customerRetrievalRate);
            newCustomer.startBuyingTickets();
        }
    }
}
public class Customer {
    private Ticketpool ticketpool;
    private int customerRetrievalRate;

    public Customer(Ticketpool ticketpool, int customerRetrievalRate) {
        this.ticketpool = ticketpool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void startBuyingTickets() {
        CustomerTask custTask = new CustomerTask(ticketpool, customerRetrievalRate);
        Thread customerThread = new Thread(custTask);
        customerThread.start();
    }
}

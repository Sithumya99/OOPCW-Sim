public class VendorTask implements Runnable {
    Ticketpool ticketpool;
    int vendorId;

    public VendorTask(Ticketpool ticketpool, int vendorId) {
        this.ticketpool = ticketpool;
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        for (int ticketId = 0; ticketId < 10; ticketId++) {
            String ticketName = vendorId + ":" + ticketId;
            Ticket ticket = new Ticket(ticketName);
            ticketpool.addTicket(ticket);
        }
    }
}

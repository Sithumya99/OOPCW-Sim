public class Vendor {

    private int vendorId;
    private Ticketpool ticketpool;

    public Vendor(int vendorId, Ticketpool ticketpool) {
        this.vendorId = vendorId;
        this.ticketpool = ticketpool;
    }

    public void startAddingTicket() {
        VendorTask venTask = new VendorTask(ticketpool, vendorId);
        Thread vendorThread = new Thread(venTask);
        vendorThread.start();
    }
}

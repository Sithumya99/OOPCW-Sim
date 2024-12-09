public class CustomerTask implements Runnable{
    private Ticketpool ticketpool;
    private int customerRetrievalRate;

    public CustomerTask(Ticketpool ticketpool, int customerRetrievalRate) {
        this.ticketpool = ticketpool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            ticketpool.buyTicket();
            try {
                Thread.sleep(customerRetrievalRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

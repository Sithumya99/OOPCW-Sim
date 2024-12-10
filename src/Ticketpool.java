import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Ticketpool {
    private Queue<Ticket> tickets;
    private int maxCapacity;
    private int ticketReleaseRate;
    private long lastAddedTime = 0L;
    private boolean isActive = false;

    public Ticketpool(int maxCapacity, int ticketReleaseRate) {
        this.tickets = new LinkedList<>();
        this.maxCapacity = maxCapacity;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public synchronized void addTicket(Ticket ticket) {
        System.out.println("start add ticket process: ");
        System.out.flush();
        if (System.currentTimeMillis() - lastAddedTime < ticketReleaseRate) {
            try {
                Thread.sleep(System.currentTimeMillis() - lastAddedTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        while (isActive) {
            try {
                System.out.println(Thread.currentThread().getName() + "waiting");
                System.out.flush();
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (Main.isRunning) {
            tickets.offer(ticket);
            lastAddedTime = System.currentTimeMillis();
            isActive = true;
            System.out.println(Thread.currentThread().getName() + ":added:" + ticket);
            System.out.flush();
        }
        notifyAll();
    }

    public synchronized void buyTicket() {
        System.out.println("start buy ticket process");
        System.out.flush();
        while (!isActive || tickets.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + "waiting");
                System.out.flush();
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (Main.isRunning) {
            tickets.poll();
            isActive = false;
            System.out.println(Thread.currentThread().getName() + ":bought ticket");
            System.out.flush();
        }
        notifyAll();
    }
}

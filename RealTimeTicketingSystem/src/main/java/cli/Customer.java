package cli;

public class Customer implements Runnable{
    private TicketPool ticketPool;
    private String customerName;

    public Customer(TicketPool ticketpool,String customerName) {
        this.ticketPool = ticketpool;
        this.customerName = customerName;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Tickets customerticket = new Tickets(customerName);
                ticketPool.getTicket(customerticket);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

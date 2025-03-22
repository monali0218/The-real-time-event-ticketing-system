package cli;

public class Vendor implements Runnable{

    private TicketPool ticketPool;
    private String vendorName;
    public Vendor(TicketPool ticketPool,String vendorName) {
        this.ticketPool = ticketPool;
        this.vendorName = vendorName;
    }

    public void run(){
        while (true) {
            try {
                Thread.sleep(1000);
                Tickets vendorTicket = new Tickets(vendorName);
                ticketPool.addTicket(vendorTicket);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

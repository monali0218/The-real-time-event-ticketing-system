package cli;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class TicketPool {
    List<Tickets> ticketList = Collections.synchronizedList(new ArrayList<Tickets>());
    private int maxTicket;


    public TicketPool(int maxTicket) {
        this.maxTicket = maxTicket;
    }

    public synchronized void addTicket(Tickets ticket) {

        while (ticketList.size() > maxTicket) {
            try {
                wait();
                System.out.println("Ticket pool is full wait for the customers to buy.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ticketList.add(ticket);
        System.out.println(ticket.getTicketName() + "add a ticket." + " Ticket pool size is " + ticketList.size());

    }

    public synchronized void getTicket(Tickets ticket) {

        while (ticketList.isEmpty()) {
            try {
                wait();
                System.out.println("Ticket pool is empty wait for the vendors to sell.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ticketList.remove(0);
        System.out.println(ticket.getTicketName() + "remove a ticket." + " Ticket pool size is " + ticketList.size());
    }

}

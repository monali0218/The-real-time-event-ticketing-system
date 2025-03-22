package com.example.RealTimeTicketingSystem.model;

import cli.TicketPool;
import cli.Tickets;

public class Customers implements Runnable{
    private TicketPooling ticketPool;
    private int customerId;

    public Customers(TicketPooling ticketpool, int customerId) {
        this.ticketPool = ticketpool;
        this.customerId = customerId;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Ticket customerticket = new Ticket(customerId);
                ticketPool.getTicket(customerticket);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

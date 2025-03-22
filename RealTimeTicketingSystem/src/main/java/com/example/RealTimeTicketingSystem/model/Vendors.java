package com.example.RealTimeTicketingSystem.model;

import cli.TicketPool;

public class Vendors implements Runnable {
    private TicketPooling ticketPool;
    private int vendorId;
    public Vendors(TicketPooling ticketPool, int vendorId) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
    }

    public void run(){
        while (true) {
            try {
                Thread.sleep(1000);
                Ticket vendorTicket = new Ticket(vendorId);
                ticketPool.addTicket(vendorTicket);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

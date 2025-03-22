package com.example.RealTimeTicketingSystem.model;

public class Ticket {
    private int ticketId;

    public Ticket(int ticketName){
        this.ticketId = ticketName;
    }

    public int getTicketName() {
        return ticketId;
    }
}

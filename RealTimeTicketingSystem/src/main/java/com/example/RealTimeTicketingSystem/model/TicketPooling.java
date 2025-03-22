package com.example.RealTimeTicketingSystem.model;

import cli.Tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPooling {
    List<Ticket> ticketList = Collections.synchronizedList(new ArrayList<Ticket>());
    private int maxTicket;

    public TicketPooling(int maxTicket) {
        this.maxTicket = maxTicket;
    }

    public synchronized void addTicket(Ticket ticket) {

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
        notifyAll();
    }

    public synchronized void getTicket(Ticket ticket) {

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
        notifyAll();
    }
}

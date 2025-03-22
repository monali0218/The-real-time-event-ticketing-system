package com.example.RealTimeTicketingSystem.controller;


import cli.Configuration;
import com.example.RealTimeTicketingSystem.model.Customers;
import com.example.RealTimeTicketingSystem.model.TicketPooling;
import com.example.RealTimeTicketingSystem.model.Vendors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/ticket-controller")
public class TicketControlling {
    //
    private int totalTickets;
    private int ticketsReleasingRate;
    private int ticketsRetrievingRate;
    private int maxTicketCapacity;
    private int numberOfVendors;
    private int numberOfCustomers;
    private TicketPooling ticketPools;
    private Configuration configuration;


    @PostMapping("/configuration")
    public String configuration(@RequestParam int totalTickets,int ticketsReleasingRate,int ticketsRetrievingRate,int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketsReleasingRate = ticketsReleasingRate;
        this.ticketsRetrievingRate =ticketsRetrievingRate;
        this.maxTicketCapacity=maxTicketCapacity;
        configuration = new Configuration(totalTickets, ticketsReleasingRate, ticketsRetrievingRate, maxTicketCapacity);
        configuration.saveConfiguration("config.json");
        ticketPools = new TicketPooling(maxTicketCapacity);
        return "Configuration successfully";
    }
    //
    @PostMapping("/set-vendors")
    public String setNoOfVendors(@RequestParam int numberOfVendors) {
        this.numberOfVendors = numberOfVendors;
        return "Vendors set successfully";
    }
    //
    @PostMapping("/set-customers")
    public String setNoOfCustomers(@RequestParam int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
        return "Customers set successfully";
    }

    @PostMapping("/start")
    public String startTheSystem(){
        if (numberOfVendors > 0) {
            for (int i = 0; i < numberOfVendors; i++) {
                Vendors vendor = new Vendors(ticketPools,(i+1));
                new Thread(vendor).start();
            }
            for (int i = 0; i < numberOfCustomers; i++) {
                Customers customer = new Customers(ticketPools,(i+1));
                new Thread(customer).start();
            }
        }
        return "System is Starting";
    }
    @GetMapping("/details")
    public ResponseEntity<Map<String, Object>> getDetails() {
        Configuration newConfiguration = configuration.loafConfiguration("config.json");//make load
        Map<String, Object> details = new HashMap<>();
        details.put ("configuration", newConfiguration);
        details.put("ticketPools", ticketPools); // Assuming TicketPooling has a proper toString() method or you can customize the details

        return ResponseEntity.ok().body(details);
    }
}
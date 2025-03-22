package cli;

import com.google.gson.Gson;

import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Configuration {
    private static final Logger logger = LogManager.getLogger(Configuration.class);
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public Configuration() {

    }



    public void saveConfiguration(String path) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(this, writer); //this refers to the current instance of the Configuration class
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        public Configuration loafConfiguration (String path){
            Gson gson = new Gson();
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                Configuration loadConfiguration = gson.fromJson(reader, Configuration.class);
                logger.info(loadConfiguration.totalTickets);
                logger.info(loadConfiguration.ticketReleaseRate);
                logger.info(loadConfiguration.customerRetrievalRate);
                logger.info(loadConfiguration.maxTicketCapacity);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }
}

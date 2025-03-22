import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/ticket-controller";

export interface ConfigurationData {
  totalTickets: number;
  ticketReleaseRate: number;
  customerRetrievalRate: number;
  maxTicketCapacity: number;
  numberOfVendors: number;
  numberOfCustomers: number;
}

export const ConfigurationService = {
  saveConfiguration: async (config: ConfigurationData) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/configuration`, null, {
        params: {
          totalTickets: config.totalTickets,
          ticketsReleasingRate: config.ticketReleaseRate,
          ticketsRetrievingRate: config.customerRetrievalRate,
          maxTicketCapacity: config.maxTicketCapacity,
        },
      });
      return response.data;
    } catch (error) {
      console.error("Error saving configuration:", error);
      throw error;
    }
  },

  setVendors: async (numberOfVendors: number) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/set-vendors`, null, {
        params: { numberOfVendors },
      });
      return response.data;
    } catch (error) {
      console.error("Error setting vendors:", error);
      throw error;
    }
  },

  setCustomers: async (numberOfCustomers: number) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/set-customers`, null, {
        params: { numberOfCustomers },
      });
      return response.data;
    } catch (error) {
      console.error("Error setting customers:", error);
      throw error;
    }
  },

  startSystem: async () => {
    try {
      const response = await axios.post(`${API_BASE_URL}/start`);
      return response.data;
    } catch (error) {
      console.error("Error starting the system:", error);
      throw error;
    }
  },

  loadConfiguration: async () => {
    try {
      const response = await axios.get<ConfigurationData>(`${API_BASE_URL}/details`);
      return response.data;
    } catch (error) {
      console.error("Error loading configuration:", error);
      throw error;
    }
  },
};
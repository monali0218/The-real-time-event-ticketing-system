import React, { useState } from "react";
import { ChevronUp, ChevronDown } from "lucide-react";
import { ConfigurationService, ConfigurationData } from "./ConfigurationService";

export function ConfigurationForm() {
  const [config, setConfig] = useState<ConfigurationData>({
    totalTickets: 0,
    ticketReleaseRate: 0,
    customerRetrievalRate: 0,
    maxTicketCapacity: 0,
    numberOfVendors: 0,
    numberOfCustomers: 0,
  });

  const handleChange = (field: keyof ConfigurationData, value: number) => {
    setConfig((prev) => ({ ...prev, [field]: value }));
  };

  const handleIncrement = (field: keyof ConfigurationData) => {
    setConfig((prev) => ({ ...prev, [field]: prev[field] + 1 }));
  };

  const handleDecrement = (field: keyof ConfigurationData) => {
    setConfig((prev) => ({ ...prev, [field]: Math.max(0, prev[field] - 1) }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await ConfigurationService.saveConfiguration(config);
      await ConfigurationService.setVendors(config.numberOfVendors);
      await ConfigurationService.setCustomers(config.numberOfCustomers);
      alert("Configuration saved successfully!");
    } catch (error) {
      console.error("Error saving configuration:", error);
      alert("Error saving configuration.");
    }
  };

  return (
    <div className="card bg-dark text-white border-0">
      <div className="card-body">
        <h2 className="card-title mb-4 text-center">Configuration Form</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Total Tickets:</label>
            <input
              type="number"
              className="form-control"
              value={config.totalTickets}
              onChange={(e) =>
                handleChange("totalTickets", parseInt(e.target.value) || 0)
              }
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Tickets Release Rate:</label>
            <input
              type="number"
              className="form-control"
              value={config.ticketReleaseRate}
              onChange={(e) =>
                handleChange("ticketReleaseRate", parseInt(e.target.value) || 0)
              }
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Customer Retrieval Rate:</label>
            <input
              type="number"
              className="form-control"
              value={config.customerRetrievalRate}
              onChange={(e) =>
                handleChange(
                  "customerRetrievalRate",
                  parseInt(e.target.value) || 0
                )
              }
            />
          </div>

          <div className="mb-4">
            <label className="form-label">Max Ticket Capacity:</label>
            <input
              type="number"
              className="form-control"
              value={config.maxTicketCapacity}
              onChange={(e) =>
                handleChange("maxTicketCapacity", parseInt(e.target.value) || 0)
              }
            />
          </div>

          <div className="row mb-4">
            <div className="col-6">
              <label className="form-label">No. of Vendors</label>
              <div className="d-flex align-items-center">
                <input
                  type="number"
                  className="form-control number-control"
                  value={config.numberOfVendors}
                  onChange={(e) =>
                    handleChange("numberOfVendors", parseInt(e.target.value) || 0)
                  }
                />
              </div>
            </div>

            <div className="col-6">
              <label className="form-label">No. of Customers</label>
              <div className="d-flex align-items-center">
                <input
                  type="number"
                  className="form-control number-control"
                  value={config.numberOfCustomers}
                  onChange={(e) =>
                    handleChange("numberOfCustomers", parseInt(e.target.value) || 0)
                  }
                />
              </div>
            </div>
          </div>

          <button type="submit" className="bg-white save-btn w-100">
            Save Configuration
          </button>
        </form>
      </div>
    </div>
  );
}
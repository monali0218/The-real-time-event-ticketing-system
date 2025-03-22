import React, { useEffect, useState } from "react";
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/ticket-controller";

export function TransactionDisplay() {
  const [transactionDetails, setTransactionDetails] = useState("");

  useEffect(() => {
    async function fetchTransactionDetails() {
      try {
        const response = await axios.get(`${API_BASE_URL}/details`);
        setTransactionDetails(JSON.stringify(response.data, null, 2));
      } catch (error) {
        console.error("Error fetching transaction details:", error);
        setTransactionDetails("Error loading transaction details.");
      }
    }

    fetchTransactionDetails();
  }, []);

  return (
    <div className="card bg-dark text-white border-0">
      <div className="card-body">
        <h2 className="card-title mb-5 text-center">Real Time Transaction</h2>
        <div className="transaction-display rounded">
          <textarea
            className="form-control bg-white text-dark border-light"
            value={transactionDetails}
            readOnly
          ></textarea>
        </div>
      </div>
    </div>
  );
}
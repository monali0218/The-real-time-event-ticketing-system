import React from "react";
import { ConfigurationForm } from "./components/ConfigurationForm";
import { TransactionDisplay } from "./components/TransactionDisplay";

function App() {
  return (
    <div className="container-fluid py-4">
      <div className="row g-4">
        <div className="col-md-6">
          <ConfigurationForm />
        </div>
        <div className="col-md-6">
          <TransactionDisplay />
        </div>
      </div>
    </div>
  );
}

export default App;

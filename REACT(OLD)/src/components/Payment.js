import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

const Payment = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const booking = location.state?.booking || {};

  const [paymentMethods, setPaymentMethods] = useState([]);
  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  // ✅ Fetch available payment methods
  useEffect(() => {
    const fetchPaymentMethods = async () => {
      try {
        const response = await axios.get("http://localhost:9000/api/ModeOfPayment");
        setPaymentMethods(response.data);
      } catch (error) {
        console.error("Error fetching payment methods:", error);
        setErrorMessage("Failed to load payment methods.");
      }
    };

    fetchPaymentMethods();
  }, []);

  if (!booking || !booking.car || !booking.customerId) {
    return <h2 className="text-center text-danger mt-4">Invalid booking details. Please try again.</h2>;
  }

  // ✅ Calculate price details
  const { car, rentalDuration, startDate, endDate } = booking;
  const totalAmount = rentalDuration * car.dailyRent;
  const tokenAmount = Math.max(totalAmount * 0.2, 500); // At least ₹500

  // ✅ Handle payment submission
  const handlePayment = async () => {
    if (!selectedPaymentMethod) {
      setErrorMessage("Please select a payment method.");
      return;
    }

    try {
      setIsLoading(true);

      // ✅ Create a payment entry
      const paymentData = {
        bookingId: booking.id,
        customerId: booking.customerId,
        carId: car.carId,
        amountPaid: tokenAmount,
        modeOfPaymentId: selectedPaymentMethod,
        paymentDate: new Date(),
      };

      const response = await axios.post("http://localhost:9000/api/Payment", paymentData);

      if (response.status === 201) {
        alert("Payment successful! Your car is booked.");
        navigate("/dashboard/customer"); // Redirect to customer dashboard
      }
    } catch (error) {
      console.error("Error processing payment:", error);
      setErrorMessage("Payment failed. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center">Confirm Payment</h2>

      {/* Error Message */}
      {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}

      <div className="card shadow-lg p-4 mt-3">
        {/* ✅ Car Details */}
        <h4>Car Details</h4>
        <img
          src={car.imageUrl || "/default-car.jpg"}
          className="img-fluid mb-3"
          alt={car.model?.modelName || "Car"}
          style={{ maxHeight: "250px", objectFit: "cover" }}
        />
        <p><strong>Model:</strong> {car.model?.modelName}</p>
        <p><strong>Daily Rent:</strong> ₹{car.dailyRent}</p>
        <p><strong>Rental Duration:</strong> {rentalDuration} days</p>
        <p><strong>From:</strong> {new Date(startDate).toDateString()}</p>
        <p><strong>To:</strong> {new Date(endDate).toDateString()}</p>

        {/* ✅ Pricing Details */}
        <h4>Price Breakdown</h4>
        <p><strong>Total Amount:</strong> ₹{totalAmount}</p>
        <p><strong>Token Amount (20%):</strong> ₹{tokenAmount}</p>

        {/* ✅ Payment Methods */}
        <h4>Payment Method</h4>
        <select className="form-control mb-3" onChange={(e) => setSelectedPaymentMethod(e.target.value)} required>
          <option value="">Select Payment Method</option>
          {paymentMethods.map((method) => (
            <option key={method.id} value={method.id}>{method.paymentMode}</option>
          ))}
        </select>

        {/* ✅ Pay Now Button */}
        <button className="btn btn-success w-100" onClick={handlePayment} disabled={isLoading}>
          {isLoading ? "Processing..." : `Pay ₹${tokenAmount} & Confirm Booking`}
        </button>
      </div>
    </div>
  );
};

export default Payment;

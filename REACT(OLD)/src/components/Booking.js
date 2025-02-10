import React, { useState, useEffect } from "react";
import axios from "axios";

const BookingManager = () => {
  const [bookings, setBookings] = useState([]);
  const [selectedBooking, setSelectedBooking] = useState(null);
  const [newBooking, setNewBooking] = useState({
    customerId: "",
    carId: "",
    rentalDuration: "",
    journeyDate: "",
    tokenAmount: "",
  });

  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  // ✅ Fetch all bookings
  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const response = await axios.get("https://localhost:7091/api/Booking");
      setBookings(response.data);
    } catch (error) {
      console.error("Error fetching bookings:", error);
      setErrorMessage("Failed to fetch bookings.");
    }
  };

  // ✅ Fetch a booking by ID
  const fetchBookingById = async (id) => {
    try {
      const response = await axios.get(`https://localhost:7091/api/Booking/${id}`);
      setSelectedBooking(response.data);
    } catch (error) {
      console.error("Error fetching booking:", error);
      setErrorMessage("Failed to fetch booking details.");
    }
  };

  // ✅ Handle input changes
  const handleInputChange = (e) => {
    setNewBooking({ ...newBooking, [e.target.name]: e.target.value });
  };

  // ✅ Add a new booking
  const addBooking = async () => {
    try {
      const response = await axios.post("https://localhost:7091/api/Booking", newBooking);
      setSuccessMessage("Booking added successfully!");
      fetchBookings(); // Refresh booking list
    } catch (error) {
      console.error("Error adding booking:", error);
      setErrorMessage("Failed to add booking.");
    }
  };

  // ✅ Update an existing booking
  const updateBooking = async (id) => {
    try {
      const response = await axios.put(`https://localhost:7091/api/Booking/${id}`, selectedBooking);
      setSuccessMessage("Booking updated successfully!");
      fetchBookings(); // Refresh booking list
    } catch (error) {
      console.error("Error updating booking:", error);
      setErrorMessage("Failed to update booking.");
    }
  };

  // ✅ Delete a booking
  const deleteBooking = async (id) => {
    try {
      await axios.delete(`https://localhost:7091/api/Booking/${id}`);
      setSuccessMessage("Booking deleted successfully!");
      fetchBookings(); // Refresh booking list
    } catch (error) {
      console.error("Error deleting booking:", error);
      setErrorMessage("Failed to delete booking.");
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center">Booking Management</h2>

      {/* Success and Error Messages */}
      {successMessage && <div className="alert alert-success">{successMessage}</div>}
      {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}

      {/* Add New Booking Form */}
      <div className="card p-4 shadow mb-4">
        <h4>Add New Booking</h4>
        <div className="mb-2">
          <input type="text" className="form-control" name="customerId" placeholder="Customer ID" onChange={handleInputChange} />
        </div>
        <div className="mb-2">
          <input type="text" className="form-control" name="carId" placeholder="Car ID" onChange={handleInputChange} />
        </div>
        <div className="mb-2">
          <input type="number" className="form-control" name="rentalDuration" placeholder="Rental Duration (Days)" onChange={handleInputChange} />
        </div>
        <div className="mb-2">
          <input type="date" className="form-control" name="journeyDate" placeholder="Journey Date" onChange={handleInputChange} />
        </div>
        <div className="mb-2">
          <input type="number" className="form-control" name="tokenAmount" placeholder="Token Amount (₹)" onChange={handleInputChange} />
        </div>
        <button className="btn btn-primary w-100" onClick={addBooking}>Add Booking</button>
      </div>

      {/* Booking List */}
      <h4>All Bookings</h4>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>ID</th>
            <th>Customer</th>
            <th>Car</th>
            <th>Duration</th>
            <th>Journey Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {bookings.map((booking) => (
            <tr key={booking.id}>
              <td>{booking.id}</td>
              <td>{booking.customerId}</td>
              <td>{booking.carId}</td>
              <td>{booking.rentalDuration} days</td>
              <td>{booking.journeyDate}</td>
              <td>
                <button className="btn btn-info btn-sm me-2" onClick={() => fetchBookingById(booking.id)}>View</button>
                <button className="btn btn-warning btn-sm me-2" onClick={() => updateBooking(booking.id)}>Edit</button>
                <button className="btn btn-danger btn-sm" onClick={() => deleteBooking(booking.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Booking Details */}
      {selectedBooking && (
        <div className="card p-4 shadow">
          <h4>Booking Details</h4>
          <p><strong>ID:</strong> {selectedBooking.id}</p>
          <p><strong>Customer ID:</strong> {selectedBooking.customerId}</p>
          <p><strong>Car ID:</strong> {selectedBooking.carId}</p>
          <p><strong>Rental Duration:</strong> {selectedBooking.rentalDuration} days</p>
          <p><strong>Journey Date:</strong> {selectedBooking.journeyDate}</p>
          <button className="btn btn-warning w-100" onClick={() => updateBooking(selectedBooking.id)}>Update Booking</button>
        </div>
      )}
    </div>
  );
};

export default BookingManager;

import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, Outlet } from "react-router-dom";

const CustomerDashboard = () => {
  const [formData, setFormData] = useState({
    userName: "",
    email: "",
    contact: "",
    address: "",
    city: { cityId: "", cityName: "" },
  });

  const [originalData, setOriginalData] = useState({});
  const [cities, setCities] = useState([]);
  const [customerId, setCustomerId] = useState(1); // Adjust to fetch customer ID dynamically
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    const fetchCustomerDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/custbyid/${customerId}`
        );
        setFormData(response.data);
        setOriginalData(response.data);
      } catch (error) {
        console.error("Error fetching customer details", error);
        setErrorMessage("Failed to fetch customer details.");
      }
    };

    const fetchCities = async () => {
      try {
        const response = await axios.get("http://localhost:8080/cities");
        setCities(response.data);
      } catch (error) {
        console.error("Error fetching cities", error);
      }
    };

    fetchCustomerDetails();
    fetchCities();
  }, [customerId]);

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === "cityId") {
      const selectedCity = cities.find(
        (city) => city.cityId === parseInt(value)
      );
      setFormData({
        ...formData,
        city: { cityId: selectedCity.cityId, cityName: selectedCity.cityName },
      });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleUpdate = async (e) => {
    e.preventDefault();

    const updatedData = {};
    Object.keys(formData).forEach((key) => {
      if (JSON.stringify(formData[key]) !== JSON.stringify(originalData[key])) {
        updatedData[key] = formData[key];
      }
    });

    if (!updatedData.city) {
      updatedData.city = originalData.city;
    }

    if (!updatedData.contact) {
      updatedData.contact = originalData.contact;
    }

    if (!updatedData.address) {
      updatedData.address = originalData.address;
    }

    try {
      const response = await axios.put(
        `http://localhost:8080/update/customer/${customerId}`,
        updatedData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      setSuccessMessage("Details updated successfully!");
      setOriginalData(response.data);
      setErrorMessage("");
    } catch (error) {
      console.error("Error updating customer details", error);
      if (error.response && error.response.data) {
        setErrorMessage(
          `Update failed: ${error.response.data.message || "Unknown error"}`
        );
      } else {
        setErrorMessage("Failed to update details. Please try again.");
      }
    }
  };

  return (
    <div className="container mt-5">
      <nav className="navbar navbar-expand-lg navbar-light bg-light shadow-sm mb-4">
        <div className="container">
          <a className="navbar-brand" href="#">
            CarRental
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNavDropdown">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item">
                <Link className="nav-link active" aria-current="page" to="logout">
                  Logout
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="row justify-content-center">
        <div className="col-md-6">
          <h2 className="text-center mb-4">Customer Dashboard</h2>
          <form
            onSubmit={handleUpdate}
            className="p-4 shadow-lg rounded bg-light"
          >
            <div className="mb-3">
              <label htmlFor="userName" className="form-label">
                Name
              </label>
              <input
                type="text"
                className="form-control"
                id="userName"
                name="userName"
                value={formData.userName}
                onChange={handleChange}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                disabled
              />
            </div>

            <div className="mb-3">
              <label htmlFor="contact" className="form-label">
                Contact
              </label>
              <input
                type="text"
                className="form-control"
                id="contact"
                name="contact"
                value={formData.contact}
                onChange={handleChange}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="address" className="form-label">
                Address
              </label>
              <input
                type="text"
                className="form-control"
                id="address"
                name="address"
                value={formData.address}
                onChange={handleChange}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="cityId" className="form-label">
                City
              </label>
              <select
                className="form-control"
                id="cityId"
                name="cityId"
                value={formData.city.cityId}
                onChange={handleChange}
              >
                <option value="">Select City</option>
                {cities.map((city) => (
                  <option key={city.cityId} value={city.cityId}>
                    {city.cityName}
                  </option>
                ))}
              </select>
            </div>

            <button
              type="submit"
              className="btn btn-primary w-100"
              style={{ transition: "background-color 0.5s ease" }}
            >
              Update Details
            </button>

            {successMessage && (
              <div className="alert alert-success mt-3" role="alert">
                {successMessage}
              </div>
            )}
            {errorMessage && (
              <div className="alert alert-danger mt-3" role="alert">
                {errorMessage}
              </div>
            )}
          </form>
        </div>
      </div>

      <Outlet />
    </div>
  );
};

export default CustomerDashboard;

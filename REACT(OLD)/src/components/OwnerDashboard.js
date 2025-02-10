import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, NavLink, Outlet } from "react-router-dom";

const OwnerDashboard = () => {
  const [formData, setFormData] = useState({
    user: {
      userName: "",
      email: "",
      password: "",
      role: { roleId: 3, roleName: "Agency" },
    },
    city: { cityId: "", cityName: "" },
    address: "",
    contact: "",
    gstNo: "",
  });

  const [originalData, setOriginalData] = useState({});
  const [cities, setCities] = useState([]);
  const [agencyId, setAgencyId] = useState(1); // Adjust to fetch agency ID dynamically
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    const fetchAgencyDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:9000/api/car-rental-agencies/${agencyId}`
        );
        setFormData(response.data);
        setOriginalData(response.data);
      } catch (error) {
        console.error("Error fetching agency details", error);
        setErrorMessage("Failed to fetch agency details.");
      }
    };

    const fetchCities = async () => {
      try {
        const response = await axios.get("http://localhost:9000/cities");
        setCities(response.data);
      } catch (error) {
        console.error("Error fetching cities", error);
      }
    };

    fetchAgencyDetails();
    fetchCities();
  }, [agencyId]);

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name.includes("user.")) {
      const field = name.split(".")[1];
      setFormData({
        ...formData,
        user: {
          ...formData.user,
          [field]: value,
        },
      });
    } else if (name === "cityId") {
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

    try {
      const response = await axios.put(
        `http://localhost:9000/api/car-rental-agencies/${agencyId}`,
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
      console.error("Error updating agency details", error);
      setErrorMessage(
        error.response?.data?.message || "Failed to update details."
      );
    }
  };

  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
        <div className="container">
          <Link className="navbar-brand" to="/">
            CarRental
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ms-auto">
              {/* Add Car Link */}
              <li className="nav-item">
                <NavLink className="nav-link" to="/add-car">
                  Add Car
                </NavLink>
              </li>
              
              {/* Logout Link */}
              <li className="nav-item">
                <NavLink className="nav-link" to="/login">
                  Logout
                </NavLink>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="container mt-5">
        <div className="row justify-content-center">
          <div className="col-lg-8">
            <h2 className="text-center mb-4">Owner Dashboard</h2>
            <form
              onSubmit={handleUpdate}
              className="p-4 shadow rounded bg-white"
            >
              <div className="mb-3">
                <label className="form-label">Name</label>
                <input
                  type="text"
                  className="form-control"
                  name="user.userName"
                  value={formData.user.userName}
                  onChange={handleChange}
                />
              </div>
              
              <div className="mb-3">
                <label className="form-label">City</label>
                <select
                  className="form-control"
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

              <button type="submit" className="btn btn-primary w-100">
                Update Details
              </button>
            </form>
          </div>
        </div>
      </div>
      
      <Outlet />
    </div>
  );
};

export default OwnerDashboard;

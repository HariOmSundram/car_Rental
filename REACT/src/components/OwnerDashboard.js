import React, { useState, useEffect } from "react";
import axios from "axios";

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
    // Fetch agency details by ID
    const fetchAgencyDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/car-rental-agencies/${agencyId}`
        );
        setFormData(response.data);
        setOriginalData(response.data); // Store the original data for comparison
      } catch (error) {
        console.error("Error fetching agency details", error);
        setErrorMessage("Failed to fetch agency details.");
      }
    };

    // Fetch list of cities
    const fetchCities = async () => {
      try {
        const response = await axios.get("http://localhost:8080/cities");
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

    // Create an updated data object with non-null properties
    const updatedData = {};
    Object.keys(formData).forEach((key) => {
      if (JSON.stringify(formData[key]) !== JSON.stringify(originalData[key])) {
        updatedData[key] = formData[key];
      }
    });

    // Include non-null required fields to avoid backend validation errors
    if (!updatedData.address) {
      updatedData.address = originalData.address; // Use the original address if unchanged
    }

    if (!updatedData.city) {
      updatedData.city = originalData.city; // Use the original city if unchanged
    }

    if (!updatedData.contact) {
      updatedData.contact = originalData.contact;
    }

    if (!updatedData.user) {
      updatedData.user = originalData.user; // Include existing user
    }

    if (!updatedData.gstNo) {
      updatedData.gstNo = originalData.gstNo;
    }
    try {
      const response = await axios.put(
        `http://localhost:8080/api/car-rental-agencies/${agencyId}`,
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

      // Handle backend error message
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
    <div className="container mt-5 d-flex justify-content-center">
      <div className="col-md-6">
        <h2 className="text-center mb-4">Owner Dashboard</h2>
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
              name="user.userName"
              value={formData.user.userName}
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
              name="user.email"
              value={formData.user.email}
              onChange={handleChange}
              disabled
            />
          </div>

          {/* <div className="mb-3">
            <label htmlFor="password" className="form-label">
              Password
            </label>
            <input
              type="password"
              className="form-control"
              id="password"
              name="user.password"
              value={formData.user.password}
              onChange={handleChange}
              disabled
            />
          </div> */}

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
            <label htmlFor="gstNo" className="form-label">
              GST Number
            </label>
            <input
              type="text"
              className="form-control"
              id="gstNo"
              name="gstNo"
              value={formData.gstNo}
              onChange={handleChange}
            />
          </div>

          <button
            type="submit"
            className="btn btn-primary w-100"
            style={{ transition: "background-color 0.5s ease" }}
          >
            Update Details
          </button>

          {/* Success and error messages */}
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
  );
};

export default OwnerDashboard;
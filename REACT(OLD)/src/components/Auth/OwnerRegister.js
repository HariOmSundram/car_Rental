import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";

const OwnerRegister = () => {
  const [cities, setCities] = useState([]);
  const [successMessage, setSuccessMessage] = useState("");
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({mode: "onChange"});

  useEffect(() => {
    const fetchCities = async () => {
      try {
        const response = await axios.get("http://localhost:9000/cities");
        setCities(response.data);
      } catch (error) {
        console.error("Error fetching cities", error);
      }
    };

    fetchCities();
  }, []);

  const onSubmit = async (data) => {
    try {
      const formData = {
        user: {
          userName: data.userName,
          email: data.email,
          password: data.password,
          role: { roleId: 3, roleName: "Agency" },
        },
        city: {
          cityId: data.cityId,
          cityName: cities.find((city) => city.cityId === parseInt(data.cityId))?.cityName || "",
        },
        companyName: data.companyName,
        address: data.address,
        contact: data.contact,
        gstNo: data.gstNo,
      };

      const response = await axios.post(
        "http://localhost:9000/api/car-rental-agencies/registerAgency",
        formData
      );
      setSuccessMessage("Owner registered successfully! Redirecting to login...");
      setTimeout(() => {
        navigate("/login");
      }, 3000);
    } catch (error) {
      console.error("Registration failed", error);
    }
  };

  return (
    <div className="container mt-5 d-flex justify-content-center">
      
      <div className="col-md-6">
        <h2 className="text-center mb-4">Register as Owner</h2>
        <form onSubmit={handleSubmit(onSubmit)} className="p-4 shadow-lg rounded bg-light">
          <div className="mb-3">
            <label htmlFor="userName" className="form-label">
              Name
            </label>
            <input
              type="text"
              className="form-control"
              id="userName"
              {...register("userName", { required: "Name is required", minLength: { value: 2, message: "Name must have at least 2 characters" } })}
            />
            {errors.userName && <p className="text-danger">{errors.userName.message}</p>}
          </div>

          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Email
            </label>
            <input
              type="email"
              className="form-control"
              id="email"
              {...register("email", {
                required: "Email is required",
                pattern: {
                  value: /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/,
                  message: "Invalid email format",
                },
              })}
            />
            {errors.email && <p className="text-danger">{errors.email.message}</p>}
          </div>

          <div className="mb-3">
            <label htmlFor="password" className="form-label">
              Password
            </label>
            <input
              type="password"
              className="form-control"
              id="password"
              {...register("password", {
                required: "Password is required",
                minLength: { value: 8, message: "Password must have at least 8 characters" },
              })}
            />
            {errors.password && <p className="text-danger">{errors.password.message}</p>}
          </div>

          <div className="mb-3">
            <label htmlFor="companyName" className="form-label">
              Company Name
            </label>
            <input
              type="text"
              className="form-control"
              id="companyName"
              {...register("companyName", { required: "Company name is required" })}
            />
            {errors.companyName && <p className="text-danger">{errors.companyName.message}</p>}
          </div>

          <div className="mb-3">
            <label htmlFor="cityId" className="form-label">
              City
            </label>
            <select
              className="form-control"
              id="cityId"
              {...register("cityId", { required: "City selection is required" })}
            >
              <option value="">Select City</option>
              {cities.map((city) => (
                <option key={city.cityId} value={city.cityId}>
                  {city.cityName}
                </option>
              ))}
            </select>
            {errors.cityId && <p className="text-danger">{errors.cityId.message}</p>}
          </div>

          <div className="mb-3">
            <label htmlFor="contact" className="form-label">
              Contact
            </label>
            <input
              type="number"
              className="form-control"
              id="contact"
              {...register("contact", {
                required: "Contact number is required",
                pattern: {
                  value: /^\d{10}$/,
                  message: "Contact number must be 10 digits",
                },
              })}
            />
            {errors.contact && <p className="text-danger">{errors.contact.message}</p>}
          </div>

          <div className="mb-3">
            <label htmlFor="address" className="form-label">
              Address
            </label>
            <input
              type="text"
              className="form-control"
              id="address"
              {...register("address", { required: "Address is required" })}
            />
            {errors.address && <p className="text-danger">{errors.address.message}</p>}
          </div>

          <div className="mb-3">
            <label htmlFor="gstNo" className="form-label">
              GST Number
            </label>
            <input
              type="text"
              className="form-control"
              id="gstNo"
              {...register("gstNo", {
                required: "GST number is required",
                pattern: {
                  value: /^[0-9]{15}$/,
                  message: "GST number must be 15 digits",
                },
              })}
            />
            {errors.gstNo && <p className="text-danger">{errors.gstNo.message}</p>}
          </div>

          <button
            type="submit"
            className="btn btn-primary w-100"
            style={{ transition: "background-color 0.5s ease" }}
          >
            Register
          </button>

          {successMessage && (
            <div className="alert alert-success mt-3" role="alert">
              {successMessage}
            </div>
          )}
        </form>
      </div>
    </div>
  );
};

export default OwnerRegister;

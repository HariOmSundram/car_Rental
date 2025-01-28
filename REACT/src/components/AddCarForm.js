import React, { useState, useEffect } from "react";
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/owner"; // Adjust based on your backend URL

const api = axios.create({
  baseURL: API_BASE_URL,
});

const AddCarForm = () => {
  const [carModels, setCarModels] = useState([]);
  const [categories, setCategories] = useState([]);
  const [fuels, setFuels] = useState([]);
  const [car, setCar] = useState({
    registrationNumber: "",
    dailyRent: "",
    kilometersRun: "",
    yearOfPurchase: "",
    category: "",
    carModel: "",
    fuel: "",
    isAvailable: true,
  });

  useEffect(() => {
    // Fetch data for car models, categories, and fuels
    api.get("/car-models").then((response) => setCarModels(response.data));
    api.get("/categories").then((response) => setCategories(response.data));
    api.get("/fuels").then((response) => setFuels(response.data));
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCar({
      ...car,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Send POST request to add the car listing
    api
      .post("/add-car", car)
      .then((response) => {
        alert("Car added successfully!");
        setCar({
          registrationNumber: "",
          dailyRent: "",
          kilometersRun: "",
          yearOfPurchase: "",
          category: "",
          carModel: "",
          fuel: "",
          isAvailable: true,
        });
      })
      .catch((error) => {
        console.error(error);
        alert("Failed to add car");
      });
  };

  return (
    <div>
      <h2>Add Car Listing</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Registration Number</label>
          <input
            type="text"
            name="registrationNumber"
            value={car.registrationNumber}
            onChange={handleInputChange}
            required
          />
        </div>
        <div>
          <label>Daily Rent</label>
          <input
            type="number"
            name="dailyRent"
            value={car.dailyRent}
            onChange={handleInputChange}
            required
          />
        </div>
        <div>
          <label>Kilometers Run</label>
          <input
            type="number"
            name="kilometersRun"
            value={car.kilometersRun}
            onChange={handleInputChange}
            required
          />
        </div>
        <div>
          <label>Year of Purchase</label>
          <input
            type="number"
            name="yearOfPurchase"
            value={car.yearOfPurchase}
            onChange={handleInputChange}
            required
          />
        </div>
        <div>
          <label>Category</label>
          <select name="category" onChange={handleInputChange} required>
            <option value="">Select Category</option>
            {categories.map((category) => (
              <option key={category.id} value={category.id}>
                {category.name}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Car Model</label>
          <select name="carModel" onChange={handleInputChange} required>
            <option value="">Select Model</option>
            {carModels.map((model) => (
              <option key={model.id} value={model.id}>
                {model.name}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Fuel Type</label>
          <select name="fuel" onChange={handleInputChange} required>
            <option value="">Select Fuel</option>
            {fuels.map((fuel) => (
              <option key={fuel.id} value={fuel.id}>
                {fuel.name}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Is Available</label>
          <input
            type="checkbox"
            name="isAvailable"
            checked={car.isAvailable}
            onChange={(e) => setCar({ ...car, isAvailable: e.target.checked })}
          />
        </div>
        <button type="submit">Add Car</button>
      </form>
    </div>
  );
};

export default AddCarForm;

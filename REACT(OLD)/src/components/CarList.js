import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const CarList = () => {
  const [cars, setCars] = useState([]);
  const [carImages, setCarImages] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [filter, setFilter] = useState({
    model: "",
    category: "",
    fuelType: "",
  });

  const [categories, setCategories] = useState([]);
  const [models, setModels] = useState([]);
  const [fuelTypes, setFuelTypes] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCars = async () => {
      try {
        const response = await axios.get("http://localhost:9000/api/cars");
        setCars(response.data);

        // ✅ Extract unique filter options
        setCategories([...new Set(response.data.map((car) => car.category?.categoryName))]);
        setModels([...new Set(response.data.map((car) => car.model?.modelName))]);
        setFuelTypes([...new Set(response.data.map((car) => car.model?.fuel?.fuelType))]);

        // ✅ Fetch images for all cars
        response.data.forEach((car) => fetchCarImages(car.carId));
      } catch (error) {
        console.error("Error fetching cars:", error);
        setError("Failed to load cars. Please try again.");
      } finally {
        setLoading(false);
      }
    };

    fetchCars();
  }, []);

  // ✅ Fetch images for each car by ID
  const fetchCarImages = async (carId) => {
    try {
      const response = await axios.get(`http://localhost:9000/api/car-images/car/${carId}`);
      if (response.data.length > 0) {
        setCarImages((prevImages) => ({ ...prevImages, [carId]: response.data[0].imageUrl }));
      }
    } catch (error) {
      console.error(`Error fetching image for car ${carId}:`, error);
    }
  };

  // ✅ Filter cars dynamically
  const filteredCars = cars.filter((car) => {
    return (
      (filter.model ? car.model?.modelName === filter.model : true) &&
      (filter.category ? car.category?.categoryName === filter.category : true) &&
      (filter.fuelType ? car.model?.fuel?.fuelType === filter.fuelType : true)
    );
  });

  // ✅ Navigate to booking page
  const handleRentNow = (car) => {
    navigate("/booking", { state: { car } });
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Available Cars for Rent</h2>

      {/* Filter Form */}
      <div className="mb-4">
        <div className="row">
          <div className="col-md-3">
            <select
              name="model"
              className="form-control"
              value={filter.model}
              onChange={(e) => setFilter({ ...filter, model: e.target.value })}
            >
              <option value="">Select Model</option>
              {models.map((model, index) => (
                <option key={index} value={model}>{model}</option>
              ))}
            </select>
          </div>

          <div className="col-md-3">
            <select
              name="category"
              className="form-control"
              value={filter.category}
              onChange={(e) => setFilter({ ...filter, category: e.target.value })}
            >
              <option value="">Select Category</option>
              {categories.map((category, index) => (
                <option key={index} value={category}>{category}</option>
              ))}
            </select>
          </div>

          <div className="col-md-3">
            <select
              name="fuelType"
              className="form-control"
              value={filter.fuelType}
              onChange={(e) => setFilter({ ...filter, fuelType: e.target.value })}
            >
              <option value="">Select Fuel Type</option>
              {fuelTypes.map((fuelType, index) => (
                <option key={index} value={fuelType}>{fuelType}</option>
              ))}
            </select>
          </div>
        </div>
      </div>

      {loading && <div className="text-center">Loading...</div>}
      {error && <div className="alert alert-danger text-center">{error}</div>}

      <div className="row">
        {filteredCars.map((car) => (
          <div className="col-md-4 mb-4" key={car.carId}>
            <div className="card shadow-sm">
              <img
                src={carImages[car.carId] || "/default-car.jpg"} // ✅ Uses car image dynamically
                className="card-img-top"
                alt="Car"
                style={{ height: "200px", objectFit: "cover" }}
              />
              <div className="card-body">
                <h5 className="card-title">{car.model?.modelName || "Unknown Model"}</h5>
                <p><strong>Daily Rent:</strong> ₹{car.dailyRent}</p>
                <p><strong>Fuel Type:</strong> {car.model?.fuel?.fuelType}</p>
                <button className="btn btn-primary w-100" onClick={() => handleRentNow(car)}>
                  Rent Now
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CarList;

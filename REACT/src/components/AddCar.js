import React, { useState, useEffect } from "react";
import axios from "axios";

const AddCar = () => {
  const [carData, setCarData] = useState({
    modelId: "",
    categoryId: "",
    agencyId: "",
    dailyRent: "",
    registrationNumber: "",
    kilometersRun: "",
    yearOfPurchase: "",
  });

  const [image, setImage] = useState(null);
  const [categories, setCategories] = useState([]);
  const [models, setModels] = useState([]);
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [user, setUser] = useState(null);

  // ✅ Fetch user, categories, and models
  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await axios.get("http://localhost:9000/api/auth/user", { withCredentials: true });
        setUser(response.data);
        setCarData((prevData) => ({
          ...prevData,
          agencyId: response.data.agencyId,
        }));
      } catch (error) {
        console.error("Error fetching user:", error);
        setMessage("Failed to fetch user data. Please log in.");
      }
    };

    const fetchData = async () => {
      try {
        const [categoryRes, modelRes] = await Promise.all([
          axios.get("http://localhost:9000/api/categories"),
          axios.get("http://localhost:9000/api/models"),
        ]);
        setCategories(categoryRes.data);
        setModels(modelRes.data);
      } catch (error) {
        console.error("Error fetching data:", error);
        setMessage("Failed to load form data.");
      }
    };

    fetchUserData();
    fetchData();
  }, []);

  // ✅ Handle input change
  const handleChange = (e) => {
    setCarData({ ...carData, [e.target.name]: e.target.value });
  };

  // ✅ Handle file selection
  const handleFileChange = (e) => {
    setImage(e.target.files[0]);
  };

  // ✅ Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Ensure required fields are filled
    if (!carData.modelId || !carData.categoryId || !carData.dailyRent || !image || !carData.agencyId) {
      setMessage("Please fill all required fields and select an image.");
      return;
    }

    setLoading(true);
    try {
      // Step 1: Add car details
      const carResponse = await axios.post("http://localhost:9000/api/cars", carData);

      if (carResponse.status !== 200) throw new Error("Car could not be added.");

      const carId = carResponse.data.carId; // Get the newly created car ID

      // Step 2: Upload car image
      const formData = new FormData();
      formData.append("files", image);
      formData.append("carId", carId);

      const imageResponse = await axios.post(
        "http://localhost:9000/api/car-images/upload-multiple",
        formData,
        { headers: { "Content-Type": "multipart/form-data" } }
      );

      if (imageResponse.status !== 200) throw new Error("Image upload failed.");

      setMessage("Car and image added successfully!");
      setCarData({
        modelId: "",
        categoryId: "",
        agencyId: user?.agencyId || "",
        dailyRent: "",
        registrationNumber: "",
        kilometersRun: "",
        yearOfPurchase: "",
      });
      setImage(null);
    } catch (error) {
      console.error("Error adding car:", error);
      setMessage(error.response?.data?.message || "Failed to add car.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center">Add New Car</h2>
      {message && <div className="alert alert-info">{message}</div>}

      <form onSubmit={handleSubmit} className="p-4 shadow rounded">
        <div className="mb-3">
          <label className="form-label">Model</label>
          <select className="form-control" name="modelId" value={carData.modelId} onChange={handleChange} required>
            <option value="">Select Model</option>
            {models.map((model) => (
              <option key={model.modelId} value={model.modelId}>
                {model.modelName}
              </option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <label className="form-label">Category</label>
          <select className="form-control" name="categoryId" value={carData.categoryId} onChange={handleChange} required>
            <option value="">Select Category</option>
            {categories.map((category) => (
              <option key={category.categoryId} value={category.categoryId}>
                {category.categoryName}
              </option>
            ))}
          </select>
        </div>

        {/* Hidden Agency ID Field */}
        <input type="hidden" name="agencyId" value={carData.agencyId} />

        <div className="mb-3">
          <label className="form-label">Daily Rent (₹)</label>
          <input type="number" className="form-control" name="dailyRent" value={carData.dailyRent} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Registration Number</label>
          <input type="text" className="form-control" name="registrationNumber" value={carData.registrationNumber} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Kilometers Run</label>
          <input type="number" className="form-control" name="kilometersRun" value={carData.kilometersRun} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label className="form-label">Year of Purchase</label>
          <input type="number" className="form-control" name="yearOfPurchase" value={carData.yearOfPurchase} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label className="form-label">Upload Image</label>
          <input type="file" className="form-control" accept="image/*" onChange={handleFileChange} required />
        </div>

        <button type="submit" className="btn btn-primary w-100" disabled={loading}>
          {loading ? "Adding..." : "Add Car"}
        </button>
      </form>
    </div>
  );
};

export default AddCar;

import { useState, useEffect } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const AddVariantForm = () => {
  const [companies, setCompanies] = useState([]);

  const [fuelTypes, setFuelTypes] = useState([]);

  let navigate = useNavigate();

  const retrieveAllCompany = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/car/company/fetch/all"
    );
    return response.data;
  };

  const retrieveFuelTypes = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/car/rental/helper/fetch/fuel-type"
    );
    return response.data;
  };

  useEffect(() => {
    const getAllCompany = async () => {
      const resCompany = await retrieveAllCompany();
      if (resCompany) {
        setCompanies(resCompany.companies);
      }
    };

    const getAllFuelTypes = async () => {
      const fuelTypes = await retrieveFuelTypes();
      if (fuelTypes) {
        setFuelTypes(fuelTypes);
      }
    };

    getAllFuelTypes();
    getAllCompany();
  }, []);

  const [selectedImage, setSelectImage] = useState(null);

  const [variantRequest, setVariantRequest] = useState({
    name: "",
    description: "",
    modelNumber: "",
    year: "",
    fuelType: "",
    isAC: true,
    seatingCapacity: "",
    pricePerDay: "",
    companyId: "",
  });

  const handleInput = (e) => {
    setVariantRequest({ ...variantRequest, [e.target.name]: e.target.value });
  };

  const saveVariant = (e) => {
    e.preventDefault();
    if (variantRequest === null) {
      toast.error("invalid input!!!", {
        position: "top-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      return;
    }

    const formData = new FormData();
    formData.append("name", variantRequest.name);
    formData.append("description", variantRequest.description);
    formData.append("modelNumber", variantRequest.modelNumber);
    formData.append("year", variantRequest.year);
    formData.append("fuelType", variantRequest.fuelType);
    formData.append("isAC", variantRequest.isAC);
    formData.append("seatingCapacity", variantRequest.seatingCapacity);
    formData.append("pricePerDay", variantRequest.pricePerDay);
    formData.append("companyId", variantRequest.companyId);
    formData.append("image", selectedImage);

    axios
      .post(
        "http://localhost:8080/api/car/variant/add",
        formData
        // , {
        //   headers: {
        //     Authorization: "Bearer " + employer_jwtToken, // Replace with your actual JWT token
        //   },
        // }
      )
      .then((resp) => {
        let response = resp.data;

        if (response.success) {
          toast.success(response.responseMessage, {
            position: "top-center",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
          });

          setTimeout(() => {
            navigate("/home");
          }, 2000); // Redirect after 3 seconds
        } else if (!response.success) {
          toast.error(response.responseMessage, {
            position: "top-center",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
          });
          setTimeout(() => {
            window.location.reload(true);
          }, 2000); // Redirect after 3 seconds
        } else {
          toast.error("It Seems Server is down!!!", {
            position: "top-center",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
          });
          // setTimeout(() => {
          //   window.location.reload(true);
          // }, 2000); // Redirect after 3 seconds
        }
      })
      .catch((error) => {
        console.error(error);
        toast.error("It seems server is down", {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        // setTimeout(() => {
        //   window.location.reload(true);
        // }, 2000); // Redirect after 3 seconds
      });
  };

  return (
    <div>
      <div class="mt-2 d-flex aligns-items-center justify-content-center mb-4">
        <div class="card form-card custom-bg" style={{ width: "60rem" }}>
          <div className="container-fluid">
            <div
              className="card-header bg-color custom-bg-text mt-2 text-center"
              style={{
                borderRadius: "1em",
                height: "45px",
              }}
            >
              <h5 class="card-title">Add Variant</h5>
            </div>
            <div class="card-body text-color">
              <form className="row g-3">
                <div className="col-md-6 mb-3">
                  <label htmlFor="title" className="form-label">
                    <b>Variant Name</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="name"
                    name="name"
                    onChange={handleInput}
                    value={variantRequest.name}
                  />
                </div>

                <div className="col-md-6 mb-3">
                  <label htmlFor="description" className="form-label">
                    <b>Variant Description</b>
                  </label>
                  <textarea
                    type="text"
                    className="form-control"
                    id="description"
                    name="description"
                    onChange={handleInput}
                    value={variantRequest.description}
                  />
                </div>

                <div className="col-md-6 mb-3">
                  <label className="form-label">
                    <b>Company</b>
                  </label>

                  <select
                    name="companyId"
                    onChange={handleInput}
                    className="form-control"
                  >
                    <option value="">Select Company</option>

                    {companies.map((company) => {
                      return (
                        <option value={company.id}> {company.name} </option>
                      );
                    })}
                  </select>
                </div>

                <div className="col-md-6 mb-3">
                  <label className="form-label">
                    <b>Model Number</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    name="modelNumber"
                    onChange={handleInput}
                    value={variantRequest.modelNumber}
                  />
                </div>

                <div className="col-md-6 mb-3">
                  <label htmlFor="title" className="form-label">
                    <b>Year</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="year"
                    name="year"
                    onChange={handleInput}
                    value={variantRequest.year}
                  />
                </div>

                <div className="col-md-6 mb-3">
                  <label className="form-label">
                    <b>Fuel Type</b>
                  </label>

                  <select
                    name="fuelType"
                    onChange={handleInput}
                    className="form-control"
                  >
                    <option value="">Select Fuel Type</option>

                    {fuelTypes.map((type) => {
                      return <option value={type}> {type} </option>;
                    })}
                  </select>
                </div>

                <div className="col-md-6 mb-3">
                  <label className="form-label">
                    <b>Is AC</b>
                  </label>

                  <select
                    name="isAC"
                    onChange={handleInput}
                    className="form-control"
                  >
                    <option value="">Select Is AC</option>
                    <option value="true">Yes</option>
                    <option value="false">No</option>
                  </select>
                </div>

                <div className="col-md-6 mb-3">
                  <label htmlFor="title" className="form-label">
                    <b>Seat Capacity</b>
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="seatingCapacity"
                    name="seatingCapacity"
                    onChange={handleInput}
                    value={variantRequest.seatingCapacity}
                  />
                </div>

                <div className="col-md-6 mb-3">
                  <label htmlFor="title" className="form-label">
                    <b>Rent Per Day</b>
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="pricePerDay"
                    name="pricePerDay"
                    onChange={handleInput}
                    value={variantRequest.pricePerDay}
                  />
                </div>

                <div className="col-md-6 mb-3">
                  <label for="formFile" class="form-label">
                    <b> Select Variant Image</b>
                  </label>
                  <input
                    class="form-control"
                    type="file"
                    id="image"
                    name="image"
                    onChange={(e) => setSelectImage(e.target.files[0])}
                    required
                  />
                </div>

                <div className="d-flex aligns-items-center justify-content-center mb-2">
                  <button
                    type="submit"
                    class="btn bg-color custom-bg-text"
                    onClick={saveVariant}
                  >
                    <b> Add Variant</b>
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddVariantForm;

import { useNavigate, useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import seat from "../images/seat.png";
import rupee from "../images/rupee.png";
import company from "../images/company.png";
import fuelType from "../images/fuel.png";
import model from "../images/model.png";
import year from "../images/year.png";
import ac from "../images/ac.png";
import rent from "../images/rent.png";

const ViewVariantDetail = () => {
  const { variantId } = useParams();

  const [variant, setVariant] = useState({
    company: {
      name: "",
    },
  });

  const customer = JSON.parse(sessionStorage.getItem("active-customer"));
  const customer_jwtToken = sessionStorage.getItem("customer-jwtToken");

  const navigate = useNavigate();

  const [booking, setBooking] = useState({
    startDate: "",
    endDate: "",
  });

  const handleBookingInput = (e) => {
    setBooking({ ...booking, [e.target.name]: e.target.value });
  };

  const retrieveVariant = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/car/variant/fetch?variantId=" + variantId
    );
    console.log(response.data);
    return response.data;
  };

  useEffect(() => {
    const getVariant = async () => {
      const res = await retrieveVariant();
      if (res) {
        setVariant(res.variants[0]);
      }
    };

    getVariant();
  }, []);

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  const bookCar = (e) => {
    if (customer === null) {
      alert("Please Login to Book Your Car!!!");
    } else {
      booking.customerId = customer.id;
      booking.vehicleId = variantId;
      fetch("http://localhost:8080/api/booking/add", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(booking),
      })
        .then((result) => {
          console.log("result", result);
          result.json().then((res) => {
            if (res.success) {
              toast.success(res.responseMessage, {
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
            } else {
              toast.error(res.responseMessage, {
                position: "top-center",
                autoClose: 1000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
              });
            }
          });
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
        });
    }

    e.preventDefault();
  };

  return (
    <div className="mb-3">
      <div className="col ml-5 mt-3 ms-5 me-5">
        <div className="card form-card custom-bg h-100 ">
          <div className="card-body">
            <div className="row">
              {/* Left side - Variant */}
              <div className="col-md-4">
                <img
                  src={"http://localhost:8080/api/car/variant/" + variant.image}
                  className="card-img-top rounded img-fluid"
                  alt="Company Logo"
                  style={{
                    maxWidth: "500px",
                  }}
                />
              </div>
              {/* Right side - Variant Details */}
              <div className="col-md-8">
                <h1 className="header-logo-color">{variant.name}</h1>
                <p className="text-color">{variant.description}</p>

                <h4 className="card-title d-flex justify-content-between header-logo-color mt-4">
                  <div className="d-flex align-items-center">
                    <img
                      src={fuelType}
                      height="30"
                      width="auto"
                      className="d-inline-block align-top me-1"
                      alt=""
                    />
                    <span className="text-color">{variant.fuelType}</span>
                  </div>

                  <div className="d-flex align-items-center">
                    <img
                      src={seat}
                      height="30"
                      width="auto"
                      className="d-inline-block align-top me-1"
                      alt=""
                    />
                    <span className="text-color">
                      {variant.seatingCapacity}
                    </span>
                  </div>

                  <div className="d-flex align-items-center">
                    <img
                      src={company}
                      height="30"
                      width="auto"
                      className="d-inline-block align-top me-1"
                      alt=""
                    />
                    <span className="text-color">{variant.company.name}</span>
                  </div>
                </h4>

                <h4 className="card-title d-flex justify-content-between header-logo-color mt-4">
                  <div className="d-flex align-items-center">
                    <img
                      src={model}
                      height="30"
                      width="auto"
                      className="d-inline-block align-top me-1"
                      alt=""
                    />
                    <span className="text-color">{variant.modelNumber}</span>
                  </div>

                  <div className="d-flex align-items-center">
                    <img
                      src={year}
                      height="30"
                      width="auto"
                      className="d-inline-block align-top me-1"
                      alt=""
                    />
                    <span className="text-color">{variant.year}</span>
                  </div>

                  <div className="d-flex align-items-center">
                    <img
                      src={ac}
                      height="30"
                      width="auto"
                      className="d-inline-block align-top me-1"
                      alt=""
                    />
                    <span className="text-color ms-2">
                      {variant.ac === true ? "Yes" : "No"}
                    </span>
                  </div>
                </h4>

                <h4 className="card-title d-flex justify-content-between header-logo-color mt-4">
                  <div className="d-flex align-items-center">
                    <img
                      src={rent}
                      height="35"
                      width="auto"
                      className="d-inline-block align-top me-1"
                      alt=""
                    />
                    <img
                      src={rupee}
                      height="30"
                      width="auto"
                      className="d-inline-block align-top me-1"
                      alt=""
                    />
                    <span className="text-color">{variant.pricePerDay}</span>
                  </div>
                </h4>

                <div className="d-flex justify-content-left mt-5">
                  <form class="row g-3" onSubmit={bookCar}>
                    <div class="col-auto">
                      <label for="from" className="text-color">
                        <h5>From</h5>
                      </label>
                      <input
                        type="date"
                        class="form-control"
                        id="startDate"
                        name="startDate"
                        onChange={handleBookingInput}
                        value={booking.checkIn}
                        required
                      />
                    </div>
                    <div class="col-auto">
                      <label for="checkout" className="text-color">
                        <h5>To</h5>
                      </label>
                      <input
                        type="date"
                        class="form-control"
                        id="endDate"
                        name="endDate"
                        onChange={handleBookingInput}
                        value={booking.checkOut}
                        required
                      />
                    </div>

                    <div className="col-auto mt-5">
                      <input
                        type="submit"
                        class="btn custom-bg bg-color mb-3"
                        value="Book Car"
                      />
                      <ToastContainer />
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewVariantDetail;

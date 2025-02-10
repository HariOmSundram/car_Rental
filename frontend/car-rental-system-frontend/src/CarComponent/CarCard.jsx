import { Link } from "react-router-dom";
import dollor from "../images/dollor_logo.png";
import seat from "../images/seat.png";
import rupee from "../images/rupee.png";
import company from "../images/company.png";
import fuelType from "../images/fuel.png";

const CarCard = (carVariant) => {
  return (
    <div className="col">
      <Link
        to={`/car/variant/${carVariant.item.id}/detail`}
        className="card car-card h-100"
        style={{ textDecoration: "none" }}
      >
        <div className="row g-0">
          {/* Left side - Company Logo */}
          <div className="col-md-4 d-flex align-items-center justify-content-center">
            <img
              src={
                "http://localhost:8080/api/car/variant/" + carVariant.item.image
              }
              className="card-img-top rounded img-fluid"
              alt="Company Logo"
            />
          </div>
          {/* Right side - Job Details */}
          <div className="col-md-8">
            <div className="card-body text-color">
              <h3 className="card-title d-flex justify-content-between header-logo-color">
                <div>
                  <b>{carVariant.item.name}</b>
                </div>
                <div className="d-flex align-items-center">
                  <img
                    src={rupee}
                    height="25"
                    width="auto"
                    className="d-inline-block align-top me-1"
                    alt=""
                  />
                  <span className="text-color">
                    {carVariant.item.pricePerDay}
                  </span>
                </div>
              </h3>

              <div className="d-flex justify-content-between text-color-second mt-3">
                <b className="text-color">
                  <img
                    src={fuelType}
                    height="28"
                    width="auto"
                    class="d-inline-block align-top me-2"
                    alt=""
                  />
                  {carVariant.item.fuelType}
                </b>
                <b className="text-color">
                  <img
                    src={seat}
                    height="30"
                    width="auto"
                    className="d-inline-block align-top me-2"
                    alt=""
                  />
                  {carVariant.item.seatingCapacity}
                </b>

                <b>
                  <img
                    src={company}
                    height="26"
                    width="auto"
                    class="d-inline-block align-top me-2"
                    alt=""
                  />
                  <span className="text-color">
                    {" "}
                    {carVariant.item.company.name}
                  </span>
                </b>
              </div>
            </div>
          </div>
        </div>
      </Link>
    </div>
  );
};

export default CarCard;

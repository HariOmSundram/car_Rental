import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate, useLocation } from "react-router-dom";

const ViewCustomerBooking = () => {
  const location = useLocation();
  const booking = location.state;

  console.log(JSON.stringify(booking));

  const admin_jwtToken = sessionStorage.getItem("admin-jwtToken");

  let navigate = useNavigate();

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  return (
    <div className="container-fluid">
      <div class="row">
        <div class="col-sm-4 mt-2">
          <div class="card form-card custom-bg">
            <img
              src={
                "http://localhost:8080/api/car/variant/" + booking.variant.image
              }
              className="card-img-top rounded img-fluid"
              alt="variant img"
            />
          </div>
        </div>
        <div class="col-sm-4 mt-2">
          <div class="card form-card custom-bg shadow-lg">
            <div
              className="card-header bg-color custom-bg-text "
              style={{
                borderRadius: "1em",
                height: "50px",
              }}
            >
              <h3 class="card-title ">{booking.variant.name}</h3>
            </div>

            <div class="card-body text-left text-color">
              <div class="text-left ">
                <h5>
                  Company :{" "}
                  <span className="header-logo-color">
                    {booking.variant.company.name}
                  </span>
                </h5>
                <h5>
                  Model Number :{" "}
                  <span className="header-logo-color">
                    {booking.variant.modelNumber}
                  </span>
                </h5>
                <h5>
                  Fuel Type :{" "}
                  <span className="header-logo-color">
                    {booking.variant.fuelType}
                  </span>
                </h5>
                <h5>
                  Price per day :{" "}
                  <span className="header-logo-color">
                    &#8377;{booking.variant.pricePerDay}
                  </span>
                </h5>
                <h5>
                  Vehicle Registration :{" "}
                  <span className="header-logo-color">
                    {booking.vehicle && booking.vehicle.registrationNumber
                      ? booking.vehicle.registrationNumber
                      : "NA"}
                  </span>
                </h5>
                <h5>
                  Booking Status :{" "}
                  <span className="header-logo-color">{booking.status}</span>
                </h5>
              </div>
            </div>
          </div>
        </div>

        <div class="col-sm-4 mt-2">
          <div className="form-card">
            <div className="container-fluid">
              <div
                className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center"
                style={{
                  borderRadius: "1em",
                  height: "38px",
                }}
              >
                <h3 className="card-title">Booking Details</h3>
              </div>
              <div className="card-body text-left text-color mt-3">
                <div class="text-left mt-3">
                  <h5>
                    Booking ID :{" "}
                    <span className="header-logo-color">
                      {booking.bookingId}
                    </span>
                  </h5>
                  <h5>
                    Booking Time :{" "}
                    <span className="header-logo-color">
                      {formatDateFromEpoch(booking.bookingTime)}
                    </span>
                  </h5>
                  <h5>
                    From Date :{" "}
                    <span className="header-logo-color">
                      {booking.startDate}
                    </span>
                  </h5>
                  <h5>
                    To Date :{" "}
                    <span className="header-logo-color">{booking.endDate}</span>
                  </h5>
                  <h5>
                    Total Price :{" "}
                    <span className="header-logo-color">
                      &#8377; {booking.totalPrice}
                    </span>
                  </h5>
                  <h5>
                    Payment Status :{" "}
                    <span className="header-logo-color">
                      {booking.payment ? "Paid" : "Pending"}
                    </span>
                  </h5>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-sm-4 mt-2">
          <div class="card form-card custom-bg shadow-lg">
            <div
              className="card-header bg-color custom-bg-text "
              style={{
                borderRadius: "1em",
                height: "50px",
              }}
            >
              <h3 class="card-title">Customer Details </h3>
            </div>

            <div class="card-body text-left text-color">
              <div class="text-left ">
                <h5>
                  Customer Name :{" "}
                  <span className="header-logo-color">
                    {booking.customer.firstName +
                      " " +
                      booking.customer.lastName}
                  </span>
                </h5>
                <h5>
                  Contact :{" "}
                  <span className="header-logo-color">
                    {booking.customer.phoneNo}
                  </span>
                </h5>
                <h5>
                  Email Id :{" "}
                  <span className="header-logo-color">
                    {booking.customer.emailId}
                  </span>
                </h5>
                <h5>
                  Address :{" "}
                  <span className="header-logo-color">
                    {booking.customer.address.street +
                      " " +
                      booking.customer.address.city +
                      " " +
                      booking.customer.address.pincode}
                  </span>
                </h5>
                <h5>
                  Vehicle Registration :{" "}
                  <span className="header-logo-color">
                    {booking.vehicle && booking.vehicle.registrationNumber
                      ? booking.vehicle.registrationNumber
                      : "NA"}
                  </span>
                </h5>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-4 mt-2">
          <div class="card form-card custom-bg shadow-lg">
            <div
              className="card-header bg-color custom-bg-text "
              style={{
                borderRadius: "1em",
                height: "50px",
              }}
            >
              <h3 class="card-title">Driving License</h3>
            </div>

            <div class="card-body text-left text-color">
              <div class="text-left ">
                <h5>
                  License Number :{" "}
                  <span className="header-logo-color">
                    {booking.customer &&
                    booking.customer.license &&
                    booking.customer.license.licenseNumber
                      ? booking.customer.license.licenseNumber
                      : "NA"}
                  </span>
                </h5>
                <h5>
                  Expiry Date :{" "}
                  <span className="header-logo-color">
                    {booking.customer &&
                    booking.customer.license &&
                    booking.customer.license.expirationDate
                      ? booking.customer.license.expirationDate
                      : "NA"}
                  </span>
                </h5>
                {(() => {
                  if (
                    booking.customer &&
                    booking.customer.license &&
                    booking.customer.license.licensePic
                  ) {
                    return (
                      <div class="d-flex aligns-items-center justify-content-center mt-3">
                        <img
                          src={
                            "http://localhost:8080/api/user/" +
                            booking.customer.license.licensePic
                          }
                          className="card-img-top rounded img-fluid"
                          alt="variant img"
                          style={{
                            maxWidth: "250px",
                            display: "inline-block",
                          }}
                        />
                      </div>
                    );
                  }
                })()}
              </div>
            </div>
          </div>
        </div>

        <div class="col-sm-4 mt-2">
          <div className="form-card">
            <div className="container-fluid">
              <div
                className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center"
                style={{
                  borderRadius: "1em",
                  height: "38px",
                }}
              >
                <h3 className="card-title">Payment Details</h3>
              </div>
              <div className="card-body text-left text-color mt-3">
                <div class="text-left mt-3">
                  <h5>
                    Payment Status :{" "}
                    <span className="header-logo-color">
                      {booking.payment ? "Paid" : "Pending"}
                    </span>
                  </h5>
                  <h5>
                    Transaction Time :{" "}
                    <span className="header-logo-color">
                      {booking.payment && booking.payment.transactionTime
                        ? formatDateFromEpoch(booking.payment.transactionTime)
                        : "Pending"}
                    </span>
                  </h5>
                  <h5>
                    Transaction Ref Id :{" "}
                    <span className="header-logo-color">
                      {booking.payment && booking.payment.transactionRefId
                        ? booking.payment.transactionRefId
                        : "Pending"}
                    </span>
                  </h5>
                  <h5>
                    Amount Paid :{" "}
                    <span className="header-logo-color">
                      &#8377; {booking.payment ? booking.totalPrice : "0.0"}
                    </span>
                  </h5>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewCustomerBooking;

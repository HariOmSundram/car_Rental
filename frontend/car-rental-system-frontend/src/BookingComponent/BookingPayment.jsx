import { useState, useEffect } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import axios from "axios";
import creditcard from "../images/credit-card.png";
import { ToastContainer, toast } from "react-toastify";

const BookingPayment = () => {
  const location = useLocation();
  var booking = location.state;

  const sessionCustomer = JSON.parse(sessionStorage.getItem("active-customer"));

  let navigate = useNavigate();

  const [paymentRequest, setPaymentRequest] = useState({
    bookingId: booking.id,
    nameOnCard: "",
    cardNo: "",
    cvv: "",
    expiryDate: "",
  });

  const handleUserInput = (e) => {
    setPaymentRequest({
      ...paymentRequest,
      [e.target.name]: e.target.value,
    });
  };

  const payAndConfirmBooking = (e) => {
    fetch("http://localhost:8080/api/booking/customer/payment", {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(paymentRequest),
    })
      .then((result) => {
        result.json().then((res) => {
          if (res.success) {
            toast.success(res.responseMessage, {
              position: "top-center",
              autoClose: 3000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });

            setTimeout(() => {
              navigate("/customer/bookings");
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
    e.preventDefault();
  };

  return (
    <div>
      <div className="d-flex align-items-center justify-content-center ms-5 mt-1 me-5 mb-3">
        <div
          className="card form-card rounded-card h-100 custom-bg"
          style={{
            maxWidth: "900px",
          }}
        >
          <div className="card-body header-logo-color">
            <h4 className="card-title text-color  text-center">
              Payment Details
            </h4>

            <div className="row mt-4">
              <div class="col-sm-1 mt-2"></div>
              <div class="col-sm-4 mt-2">
                <img
                  src={creditcard}
                  className="card-img-top rounded img-fluid"
                  alt="img"
                  style={{
                    maxWidth: "500px",
                  }}
                />
              </div>
              <div class="col-sm-4 mt-2">
                <form className="row g-3" onSubmit={payAndConfirmBooking}>
                  <div className=" text-color">
                    <label htmlFor="title" className="form-label">
                      <b>Name on Card</b>
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="nameOnCard"
                      name="nameOnCard"
                      onChange={handleUserInput}
                      value={paymentRequest.nameOnCard}
                      placeholder="Name Surname"
                      required
                    />
                  </div>
                  <div className="mb-3 text-color">
                    <label htmlFor="title" className="form-label">
                      <b>Card Number</b>
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="cardNo"
                      name="cardNo"
                      onChange={handleUserInput}
                      value={paymentRequest.cardNo}
                      placeholder="0000 0000 0000 0000"
                      required
                    />
                  </div>

                  <div className="col text-color">
                    <label htmlFor="title" className="form-label">
                      <b>Valid Through</b>
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="expiryDate"
                      name="expiryDate"
                      onChange={handleUserInput}
                      value={paymentRequest.expiryDate}
                      placeholder="01/24"
                      required
                    />
                  </div>

                  <div className="col text-color">
                    <label htmlFor="title" className="form-label">
                      <b>CVV</b>
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      id="cvv"
                      name="cvv"
                      onChange={handleUserInput}
                      value={paymentRequest.cvv}
                      placeholder="123"
                      required
                    />
                  </div>

                  <input
                    type="submit"
                    className="btn bg-color custom-bg-text ms-2 "
                    value={`PAY ₹ ${booking.totalPrice}`}
                  />
                  <ToastContainer />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BookingPayment;

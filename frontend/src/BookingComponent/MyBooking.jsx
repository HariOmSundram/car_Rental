import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { Button, Modal } from "react-bootstrap";

const MyBooking = () => {
  const [bookings, setBookings] = useState([]);
  const customer_jwtToken = sessionStorage.getItem("customer-jwtToken");

  const user = JSON.parse(sessionStorage.getItem("active-customer"));

  let navigate = useNavigate();

  const retrieveAllBookings = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/booking/fetch/customer-wise?customerId=" +
        user.id
    );
    return response.data;
  };

  useEffect(() => {
    const getAllBooking = async () => {
      const res = await retrieveAllBookings();
      if (res) {
        setBookings(res.bookings);
      }
    };

    getAllBooking();
  }, []);

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  const cancelBooking = (e, bookingId) => {
    console.log(bookingId);
    if (!bookingId) {
      toast.error("Missing Input", {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    } else {
      let data = {
        status: "Cancel",
        bookingId: bookingId,
      };

      fetch("http://localhost:8080/api/booking/cancel", {
        method: "DELETE",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          //     Authorization: "Bearer " + admin_jwtToken,
        },
        body: JSON.stringify(data),
      })
        .then((result) => {
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
                window.location.reload(true);
              }, 1000); // Redirect after 3 seconds
            } else if (!res.success) {
              toast.error(res.responseMessage, {
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
              }, 1000); // Redirect after 3 seconds
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
          setTimeout(() => {
            window.location.reload(true);
          }, 1000); // Redirect after 3 seconds
        });
    }
  };

  const viewCustomerBookingDetail = (booking) => {
    navigate("/customer/vehicle/booking/details", { state: booking });
  };

  const payAndConfirm = (booking) => {
    navigate("/customer/booking/payment", { state: booking });
  };

  return (
    <div className="mt-3">
      <div
        className="card form-card ms-2 me-2 mb-5 custom-bg"
        style={{
          height: "45rem",
        }}
      >
        <div
          className="card-header custom-bg-text text-center bg-color"
          style={{
            borderRadius: "1em",
            height: "50px",
          }}
        >
          <h2>All Bookings</h2>
        </div>
        <div
          className="card-body"
          style={{
            overflowY: "auto",
          }}
        >
          <div className="table-responsive">
            <table className="table text-color text-center">
              <thead className="table-bordered border-color bg-color custom-bg-text">
                <tr>
                  <th scope="col">Variant</th>
                  <th scope="col">Name</th>
                  <th scope="col">Booking Id</th>
                  <th scope="col">Total Day</th>
                  <th scope="col">Price</th>
                  <th scope="col">Customer</th>
                  <th scope="col">Booking Time</th>
                  <th scope="col">From</th>
                  <th scope="col">To</th>
                  <th scope="col">Status</th>
                  <th scope="col">Vehicle</th>
                  <th scope="col">Payment</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody className="header-logo-color">
                {bookings.map((booking) => {
                  return (
                    <tr>
                      <td>
                        <img
                          src={
                            "http://localhost:8080/api/car/variant/" +
                            booking.variant.image
                          }
                          class="img-fluid"
                          alt="car_pic"
                          style={{
                            maxWidth: "90px",
                          }}
                        />
                      </td>
                      <td>
                        <b>{booking.variant.name}</b>
                      </td>
                      <td>
                        <b>{booking.bookingId}</b>
                      </td>
                      <td>
                        <b>{booking.totalDay}</b>
                      </td>
                      <td>
                        <b>&#8377;{booking.totalPrice}</b>
                      </td>
                      <td>
                        <b>
                          {booking.customer.firstName +
                            " " +
                            booking.customer.lastName}
                        </b>
                      </td>
                      <td>
                        <b>{formatDateFromEpoch(booking.bookingTime)}</b>
                      </td>
                      <td>
                        <b>{booking.startDate}</b>
                      </td>
                      <td>
                        <b>{booking.endDate}</b>
                      </td>
                      <td>
                        <b>{booking.status}</b>
                      </td>
                      <td>
                        <b>
                          {booking.vehicle
                            ? booking.vehicle.registrationNumber
                            : "NA"}
                        </b>
                      </td>
                      <td>
                        <b>{booking.payment ? "Paid" : "Pending"}</b>
                      </td>
                      <td>
                        {(() => {
                          if (booking.status === "Approved") {
                            return (
                              <button
                                onClick={() => payAndConfirm(booking)}
                                className="btn btn-sm bg-color custom-bg-text"
                              >
                                <b>Pay & Confirm</b>
                              </button>
                            );
                          }
                        })()}

                        {(() => {
                          if (
                            booking.status !== "Paid & Confirmed" &&
                            booking.status !== "Cancelled"
                          ) {
                            return (
                              <button
                                type="button"
                                onClick={(e) => cancelBooking(e, booking.id)}
                                className="btn btn-sm bg-color custom-bg-text mt-2"
                              >
                                <b>Cancel</b>
                              </button>
                            );
                          }
                        })()}

                        <button
                          onClick={() => viewCustomerBookingDetail(booking)}
                          className="btn btn-sm bg-color custom-bg-text mt-2"
                        >
                          <b>View</b>
                        </button>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyBooking;

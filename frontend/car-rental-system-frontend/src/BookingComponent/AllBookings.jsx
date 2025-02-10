import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { Button, Modal } from "react-bootstrap";

const AllBookings = () => {
  const [bookings, setBookings] = useState([]);
  const admin_jwtToken = sessionStorage.getItem("admin-jwtToken");

  const [booking, setBooking] = useState({});
  const [vehicles, setVehicles] = useState([]);
  const [vehicleId, setVehicleId] = useState("");
  const [status, setStatus] = useState("");

  const [variantId, setVariantId] = useState("");

  const [assignBooking, setAssignBooking] = useState({});

  const [showModal, setShowModal] = useState(false);

  const handleClose = () => setShowModal(false);
  const handleShow = () => setShowModal(true);

  const assignBookingVehicle = (booking, e) => {
    setAssignBooking(booking);
    setVariantId(booking.variant.id);
    handleShow();
  };

  let navigate = useNavigate();

  const retrieveAllBookings = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/booking/fetch/all"
    );
    return response.data;
  };

  const retrieveVehiclesByVariant = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/car/vehicle/fetch/variant-wise?variantId=" +
        variantId
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

    const getAllVariantVehicles = async () => {
      const res = await retrieveVehiclesByVariant();
      if (res) {
        setVehicles(res.vehicles);
      }
    };

    if (variantId !== "") {
      getAllVariantVehicles();
    }

    getAllBooking();
  }, [assignBooking]);

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  const updateCustomerBookingStatus = (e) => {
    console.log(assignBooking);
    console.log(status);

    if (assignBooking === null || status === "") {
      toast.error("Missing Input", {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    } else if (status === "Approved" && vehicleId === "") {
      toast.error("Select Vehicle!!!", {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    } else {
      let data =
        status === "Rejected"
          ? { status: status, bookingId: assignBooking.id }
          : {
              status: status,
              bookingId: assignBooking.id,
              vehicleId: vehicleId,
            };

      console.log(data);

      fetch("http://localhost:8080/api/booking/update/assign/vehicle", {
        method: "PUT",
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
    e.preventDefault();
  };

  const viewCustomerBookingDetail = (booking) => {
    navigate("/customer/vehicle/booking/details", { state: booking });
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
                            "http://localhost:8080/api/variant/" +
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
                          if (booking.status === "Pending") {
                            return (
                              <button
                                onClick={() => assignBookingVehicle(booking)}
                                className="btn btn-sm bg-color custom-bg-text"
                              >
                                <b>Update</b>
                              </button>
                            );
                          }
                        })()}

                        <button
                          onClick={() => viewCustomerBookingDetail(booking)}
                          className="btn btn-sm bg-color custom-bg-text"
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

      <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton className="bg-color custom-bg-text">
          <Modal.Title
            style={{
              borderRadius: "1em",
            }}
          >
            Update Booking Status
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="ms-3 mt-3 mb-3 me-3">
            <form>
              <div class="mb-3">
                <label for="title" class="form-label">
                  <b>Booking Id</b>
                </label>
                <input
                  type="text"
                  class="form-control"
                  value={assignBooking.bookingId}
                  readOnly
                />
              </div>

              <div className=" mb-3">
                <label className="form-label">
                  <b>Status</b>
                </label>

                <select
                  name="status"
                  onChange={(e) => setStatus(e.target.value)}
                  className="form-control"
                >
                  <option value="">Select Status</option>
                  <option value="Approved">Approved</option>
                  <option value="Rejected">Rejected</option>
                </select>
              </div>

              {(() => {
                if (status === "Approved") {
                  return (
                    <div className=" mb-3">
                      <label className="form-label">
                        <b>Vehicle</b>
                      </label>

                      <select
                        name="vehicleId"
                        onChange={(e) => setVehicleId(e.target.value)}
                        className="form-control"
                      >
                        <option value="">Select Vehicle</option>

                        {vehicles.map((vehicle) => {
                          return (
                            <option value={vehicle.id}>
                              {vehicle.registrationNumber}
                            </option>
                          );
                        })}
                      </select>
                    </div>
                  );
                }
              })()}

              <div className="d-flex aligns-items-center justify-content-center mb-2">
                <button
                  type="submit"
                  onClick={updateCustomerBookingStatus}
                  class="btn bg-color custom-bg-text"
                >
                  Udpate Status
                </button>
                <ToastContainer />
              </div>

              <ToastContainer />
            </form>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default AllBookings;

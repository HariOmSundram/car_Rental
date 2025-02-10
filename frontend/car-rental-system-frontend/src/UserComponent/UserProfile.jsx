import { useState, useEffect } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import axios from "axios";

const UserProfile = () => {
  const location = useLocation();
  var customer = location.state;

  const sessionCustomer = JSON.parse(sessionStorage.getItem("active-customer"));

  const [user, setUser] = useState(customer);

  let navigate = useNavigate();

  const addDrivingLicense = (booking) => {
    navigate("/customer/driving-license/add");
  };

  const retrieveUser = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/user/fetch/user-id?userId=" + customer.id
    );
    return response.data;
  };

  useEffect(() => {
    const getUser = async () => {
      const res = await retrieveUser();
      if (res) {
        setUser(res.users[0]);
      }
    };

    getUser();
  }, []);

  return (
    <div>
      {/* User Profile Card */}
      <div className="d-flex align-items-center justify-content-center ms-5 mt-1 me-5 mb-3">
        <div
          className="card form-card rounded-card h-100 custom-bg"
          style={{
            width: "900px",
          }}
        >
          <div className="card-body header-logo-color">
            <h4 className="card-title text-color  text-center">
              Personal Detail
            </h4>

            <div className="row mt-4">
              <div className="col-md-4">
                <p className="mb-2">
                  <b className="text-color">First Name:</b> {user.firstName}
                </p>
              </div>
              <div className="col-md-4">
                <p className="mb-2">
                  <b className="text-color">Last Name:</b> {user.lastName}
                </p>
              </div>
              <div className="col-md-4">
                <p className="mb-2">
                  <b className="text-color">Email Id:</b> {user.emailId}
                </p>
              </div>
            </div>
            <div className="row mt-2">
              <div className="col-md-4">
                <p className="mb-2">
                  <b className="text-color">Contact:</b> {user.phoneNo}
                </p>
              </div>
              <div className="col-md-4">
                <p className="mb-2">
                  <b className="text-color">Address:</b>{" "}
                  {user.address.street +
                    " " +
                    user.address.city +
                    " " +
                    user.address.pincode}
                </p>
              </div>
            </div>
            <h4 className="card-title text-color  text-center mt-5">
              Driving License
            </h4>
            {(() => {
              if (user.license) {
                return (
                  <div>
                    <div className="row mt-4">
                      <div className="col-md-4">
                        <p className="mb-2">
                          <b className="text-color">License No:</b>{" "}
                          {user.license.licenseNumber}
                        </p>
                      </div>
                      <div className="col-md-4">
                        <p className="mb-2">
                          <b className="text-color">License Expiry:</b>{" "}
                          {user.license.expirationDate}
                        </p>
                      </div>

                      <div class="d-flex aligns-items-center justify-content-center mt-3">
                        <img
                          src={
                            "http://localhost:8080/api/user/" +
                            user.license.licensePic
                          }
                          className="card-img-top rounded img-fluid"
                          alt="variant img"
                          style={{
                            maxWidth: "350px",
                            display: "inline-block",
                          }}
                        />
                      </div>
                    </div>
                  </div>
                );
              } else if (
                sessionCustomer &&
                sessionCustomer.role === "Customer"
              ) {
                return (
                  <div className="d-flex aligns-items-center justify-content-center">
                    <button
                      onClick={(e) => addDrivingLicense()}
                      className="btn btn-md bg-color custom-bg-text mt-4 "
                    >
                      <b>Add License</b>
                    </button>
                  </div>
                );
              } else {
                return (
                  <div className="text-center header-logo-color mt-4">
                    <h5>Not Uploaded</h5>
                  </div>
                );
              }
            })()}
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserProfile;

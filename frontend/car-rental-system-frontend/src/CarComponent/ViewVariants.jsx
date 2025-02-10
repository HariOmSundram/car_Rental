import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const ViewVariants = () => {
  const [variants, setVariants] = useState([]);
  const admin_jwtToken = sessionStorage.getItem("admin-jwtToken");

  let navigate = useNavigate();

  const retrieveAllVariant = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/car/variant/fetch/all"
    );
    return response.data;
  };

  useEffect(() => {
    const getAllVariant = async () => {
      const res = await retrieveAllVariant();
      if (res) {
        setVariants(res.variants);
      }
    };
    getAllVariant();
  }, []);

  const deleteVariant = (variantId, e) => {
    fetch("http://localhost:8080/api/car/variant/delete?variantId=" + variantId, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        //     Authorization: "Bearer " + admin_jwtToken,
      },
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
  };

  const updateVariant = (variant) => {
    navigate("/admin/variant/update", { state: variant });
  };

  const viewVehicles = (variant) => {
    navigate("/admin/vehicle/details", { state: variant });
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
          <h2>All Variants</h2>
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
                  <th scope="col">Company</th>
                  <th scope="col">Description</th>
                  <th scope="col">Fuel Type</th>
                  <th scope="col">Seat Capacity</th>
                  <th scope="col">Model</th>
                  <th scope="col">Year</th>
                  <th scope="col">Is AC</th>
                  <th scope="col">Price</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody className="header-logo-color">
                {variants.map((variant) => {
                  return (
                    <tr>
                      <td>
                        <img
                          src={
                            "http://localhost:8080/api/car/variant/" + variant.image
                          }
                          class="img-fluid"
                          alt="car_pic"
                          style={{
                            maxWidth: "90px",
                          }}
                        />
                      </td>
                      <td>
                        <b>{variant.name}</b>
                      </td>
                      <td>
                        <b>{variant.company.name}</b>
                      </td>
                      <td>
                        <b>{variant.description}</b>
                      </td>
                      <td>
                        <b>{variant.fuelType}</b>
                      </td>
                      <td>
                        <b>{variant.seatingCapacity}</b>
                      </td>
                      <td>
                        <b>{variant.modelNumber}</b>
                      </td>
                      <td>
                        <b>{variant.year}</b>
                      </td>
                      <td>
                        <b>{variant.ac === true ? "Yes" : "No"}</b>
                      </td>
                      <td>
                        <b>{variant.pricePerDay}</b>
                      </td>
                      <td>
                        <button
                          onClick={() => updateVariant(variant)}
                          className="btn btn-sm bg-color custom-bg-text"
                        >
                          <b> Update</b>
                        </button>
                        <button
                          onClick={() => deleteVariant(variant.id)}
                          className="btn btn-sm bg-color custom-bg-text mt-2"
                        >
                          <b>Delete</b>
                        </button>

                        <button
                          onClick={() => viewVehicles(variant)}
                          className="btn btn-sm bg-color custom-bg-text mt-2"
                        >
                          <b>View Vehicles</b>
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

export default ViewVariants;

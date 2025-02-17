import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import Carousel from "./Carousel";
import Footer from "../NavbarComponent/Footer";
import { useNavigate } from "react-router-dom";
import CarCard from "../CarComponent/CarCard";

const HomePage = () => {
  const navigate = useNavigate();

  const [variants, setVariants] = useState([]);
  const [companies, setCompanies] = useState([]);

  const [companyId, setCompanyId] = useState("");

  const [tempCompanyId, setTempCompanyId] = useState("");

  const retrieveAllCompany = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/car/company/fetch/all"
    );
    return response.data;
  };

  const retrieveAllVariant = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/car/variant/fetch/all"
    );
    return response.data;
  };

  const retrieveAllVariantByCompanyId = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/variant/fetch/company-wise?companyId=" +
        companyId
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

    const getAllVariant = async () => {
      if (companyId !== "") {
        const res = await retrieveAllVariantByCompanyId();
        if (res) {
          setVariants(res.variants);
        }
      } else {
        const res = await retrieveAllVariant();
        if (res) {
          setVariants(res.variants);
        }
      }
    };
    getAllCompany();
    getAllVariant();
  }, [companyId]);

  const searchCompany = (e) => {
    e.preventDefault();
    setCompanyId(tempCompanyId);
  };

  return (
    <div
      className="container-fluid mb-2"
      style={{
        backgroundColor: "#1f1f1f",
      }}
    >
      <Carousel />

      <h4 className="text-color text-center mt-3">Search Cars here..!!</h4>
      <div className="d-flex aligns-items-center justify-content-center mt-3">
        <form class="row g-3">
          <div class="col-auto">
            <select
              onChange={(e) => setTempCompanyId(e.target.value)}
              className="form-control"
              required
            >
              <option value="">Select Car Company..</option>

              {companies.map((company) => {
                return <option value={company.id}> {company.name} </option>;
              })}
            </select>
          </div>

          <div class="col-auto">
            <button
              type="submit"
              class="btn bg-color custom-bg-text mb-3"
              onClick={searchCompany}
            >
              <b>Search</b>
            </button>
          </div>
        </form>
      </div>

      <div className="col-md-12 mt-3 mb-5">
        <div className="row row-cols-1 row-cols-md-2 g-4">
          {variants.map((variant) => {
            return <CarCard item={variant} key={variant.id} />;
          })}
        </div>
      </div>
      <hr />
      <Footer />
    </div>
  );
};

export default HomePage;

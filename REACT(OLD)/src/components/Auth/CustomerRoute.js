import React from "react";
import { Navigate } from "react-router-dom";
import { useSelector } from "react-redux";

const CustomerRoute = ({ children }) => {
  const { isAuthenticated, role } = useSelector((state) => state.auth);

  // Check if the user is authenticated and has the "Customer" role
  if (!isAuthenticated || role?.roleName !== "Customer") {
    return <Navigate to="/login" replace />;
  }

  // If the user is authenticated and is a Customer, render the children
  return children;
};

export default CustomerRoute;

import React from "react";
import { Navigate } from "react-router-dom";
import { useSelector } from "react-redux";

const OwnerRoute = ({ children }) => {
  const { isAuthenticated, role } = useSelector((state) => state.auth);

  // Check if the user is authenticated and has the "Owner" role
  if (!isAuthenticated || role?.roleName !== "Agency") {
    return <Navigate to="/login" replace />;
  }

  // If the user is authenticated and is an Owner, render the children
  return children;
};

export default OwnerRoute;

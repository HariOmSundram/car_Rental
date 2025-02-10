import React from "react";
import { Navigate } from "react-router-dom";
import { useSelector } from "react-redux";

const AdminRoute = ({ children }) => {
  const { isAuthenticated, role } = useSelector((state) => state.auth);

  // Check if the user is authenticated and has the "Admin" role
  if (!isAuthenticated || role?.roleName !== "Admin") {
    return <Navigate to="/login" replace />;
  }

  // If the user is authenticated and is an Admin, render the children
  return children;
};

export default AdminRoute;
import React from "react";
import { Routes, Route, useLocation } from "react-router-dom";
import { useDispatch } from "react-redux";
import { logout } from "./redux/slices/authSlice";
import Header from "./components/Header";
import AboutUs from "./components/AboutUs";
import Services from "./components/Services";
import Contact from "./components/Contact";
import Login from "./components/Auth/Login";
import Register from "./components/Auth/Register";
import Dashboard from "./components/Dashboard";
import CustomerRegister from "./components/Auth/CustomerRegister";
import OwnerRegister from "./components/Auth/OwnerRegister";
import OwnerRoute from "./components/Auth/OwnerRoute";
import CustomerRoute from "./components/Auth/CustomerRoute";
import AdminRoute from "./components/AdminRoute";
import CustomerDashboard from "./components/CustomerDashboard";
import OwnerDashboard from "./components/OwnerDashBoard";
import Admin from "./components/Auth/Admin";
import ViewUsers from "./components/ViewUsers";
import CarList from "./components/CarList";
import Booking from "./components/Booking";
import AddCar from "./components/AddCar";
import HomePage from "./components/HomePage";
import { Link } from "react-router-dom"; // ✅ Added to ensure navigation works
import Payment from "./components/Payment";

// ✅ Logout Handler Component
const LogoutHandler = () => {
  const dispatch = useDispatch();
  return (
    <button
      className="btn btn-danger"
      onClick={() => {
        dispatch(logout());
        window.location.href = "/login"; // ✅ Ensure full reload for proper session handling
      }}
    >
      Logout
    </button>
  );
};

const App = () => {
  const location = useLocation();

  // Paths where the header should not be shown
  const noHeaderPaths = ["/dashboard/customer", "/dashboard/owner", "/dashboard/admin"];

  return (
    <div>
      {/* ✅ Display Header except for specific paths */}
      {!noHeaderPaths.some(path => location.pathname.startsWith(path)) && <Header />}

  

      <Routes>
        {/* ✅ Public Routes */}
        <Route path="/" element={<HomePage />} />
        <Route path="/about" element={<AboutUs />} />
        <Route path="/services" element={<Services />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/register/customer" element={<CustomerRegister />} />
        <Route path="/register/owner" element={<OwnerRegister />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/add-car" element={<AddCar />} />
        <Route path="booking" element={<Booking/>}/>
        <Route path="payment" element={<Payment/>}/>
        <Route path="view-car" element={<CarList/>}/>
        <Route path="viewCust" element={<CustomerDashboard/>}/>
        <Route path="viewOwn" element={<OwnerDashboard/>}/>

        

        {/* ✅ Owner Routes */}
        <Route
          path="/dashboard/owner"
          element={
            <OwnerRoute>
              <OwnerDashboard />
            </OwnerRoute>
          }
        />
        <Route path="/dashboard/owner/logout" element={<OwnerRoute><LogoutHandler /></OwnerRoute>} />

        {/* ✅ Customer Routes */}
        <Route
          path="/dashboard/customer"
          element={
            <CustomerRoute>
              <CustomerDashboard />
            </CustomerRoute>
          }
        />
        <Route path="/dashboard/customer/logout" element={<CustomerRoute><LogoutHandler /></CustomerRoute>} />
        <Route path="/dashboard/customer/view-cars" element={<CustomerRoute><CarList /></CustomerRoute>} />

        {/* ✅ Booking Route */}
        <Route path="/booking" element={<Booking />} />

        {/* ✅ Admin Routes */}
        <Route path="/dashboard/admin" element={<AdminRoute><Admin /></AdminRoute>} />
        <Route path="/dashboard/admin/users" element={<AdminRoute><ViewUsers /></AdminRoute>} />
        <Route path="/dashboard/admin/logout" element={<AdminRoute><LogoutHandler /></AdminRoute>} />

        {/* ✅ Wildcard Route for 404 Pages */}
        <Route path="*" element={<h2 className="text-center mt-5">404 - Page Not Found</h2>} />
      </Routes>
    </div>
  );
};

export default App;

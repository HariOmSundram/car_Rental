import React from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  useLocation,
  useNavigate,
} from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logout } from './redux/slices/authSlice';
import Header from './components/Header';
import WebsiteHome from './components/WebsiteHome';
import AboutUs from './components/AboutUs';
import Services from './components/Services';
import Contact from './components/Contact';
import Login from './components/Auth/Login';
import Register from './components/Auth/Register';
import Dashboard from './components/Dashboard';
import CustomerRegister from './components/Auth/CustomerRegister';
import OwnerRegister from './components/Auth/OwnerRegister';
import OwnerRoute from './components/Auth/OwnerRoute';
import CustomerRoute from './components/Auth/CustomerRoute';
import AdminRoute from './components/AdminRoute';
import CustomerDashboard from './components/CustomerDashboard';
import OwnerDashboard from './components/OwnerDashboard';
import Admin from './components/Auth/Admin';
import ViewUsers from './components/ViewUsers';
import Logout from './components/Logout';

const App = () => {
  const location = useLocation();
  const dispatch = useDispatch();

  const noHeaderPaths = [
    '/dashboard/customer',
    '/dashboard/owner',
    '/dashboard/admin',
  ];

  const handleLogout = () => {
    dispatch(logout());
  };

  return (
    <div>
      {!noHeaderPaths.includes(location.pathname) && <Header />}
      <Routes>
        <Route path="/" element={<WebsiteHome />} />
        <Route path="/about" element={<AboutUs />} />
        <Route path="/services" element={<Services />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/register/customer" element={<CustomerRegister />} />
        <Route path="/register/owner" element={<OwnerRegister />} />
        <Route path="/dashboard" element={<Dashboard />} />

        {/*  Routes for Owner */}
        <Route
          path="/dashboard/owner"
          element={
            <OwnerRoute>
              <OwnerDashboard />
            </OwnerRoute>
          }
        />
        <Route
          path="/dashboard/owner/logout"
          element={
            <OwnerRoute>
              <Logout handleLogout={handleLogout} />
            </OwnerRoute>
          }
        />

        {/*  Routes for Customer */}
        <Route
          path="/dashboard/customer"
          element={
            <CustomerRoute>
              <CustomerDashboard />
            </CustomerRoute>
          }
        />
        <Route
          path="/dashboard/customer/logout"
          element={
            <CustomerRoute>
              <Logout handleLogout={handleLogout} />
            </CustomerRoute>
          }
        />

        {/*  Routes for Admin */}
        <Route
          path="/dashboard/admin"
          element={
            <AdminRoute>
              <Admin />
            </AdminRoute>
          }
        />
        <Route
          path="/dashboard/admin/users"
          element={
            <AdminRoute>
              <ViewUsers />
            </AdminRoute>
          }
        />
        <Route
          path="/dashboard/admin/logout"
          element={
            <AdminRoute>
              <Logout handleLogout={handleLogout} />
            </AdminRoute>
          }
        />
      </Routes>
    </div>
  );
};

export default App;

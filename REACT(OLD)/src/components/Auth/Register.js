import React from 'react';
import { Link } from 'react-router-dom';

const Register = () => {
  return (
    <div className="container mt-5">
      <h2>Register</h2>
      <div className="mt-4">
        <Link to="/register/customer" className="btn btn-primary me-3">Register as Customer</Link>
        <Link to="/register/owner" className="btn btn-secondary">Register as Owner</Link>
      </div>
    </div>
  );
};

export default Register;
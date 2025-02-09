import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useForm } from 'react-hook-form';

const CustomerRegister = () => {
  const [cities, setCities] = useState([]);
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({mode: "onChange"});

  useEffect(() => {
    const fetchCities = async () => {
      try {
        const response = await axios.get('http://localhost:9000/cities');
        setCities(response.data);
      } catch (error) {
        console.error('Failed to fetch cities', error);
      }
    };

    fetchCities();
  }, []);

  const onSubmit = async (data) => {
    const formData = {
      user: {
        userName: data.userName,
        email: data.email,
        password: data.password,
        role: { roleId: 2, roleName: 'Customer' },
      },
      city: {
        cityId: data.cityId,
        cityName: cities.find((city) => city.cityId === parseInt(data.cityId))?.cityName || '',
      },
      address: data.address,
      adharNumber: data.adharNumber,
      drivingLicenseNo: data.drivingLicenseNo,
      contact: data.contact,
    };

    try {
      await axios.post('http://localhost:9000/register/customer', formData);
      alert('Customer registered successfully!');
      navigate('/login');
    } catch (error) {
      console.error('Registration failed', error);
      alert('Registration failed, please try again.');
    }
  };

  return (
    <div className="container mt-5">
      <h2>Register as Customer</h2>

      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="mb-3">
          <label htmlFor="userName" className="form-label">Name</label>
          <input
            type="text"
            className="form-control"
            id="userName"
            {...register('userName', { required: 'Name is required', minLength: { value: 2, message: 'Name must have at least 2 characters' } })}
          />
          {errors.userName && <p className="text-danger">{errors.userName.message}</p>}
        </div>

        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email</label>
          <input
            type="email"
            className="form-control"
            id="email"
            {...register('email', {
              required: 'Email is required',
              pattern: {
                value: /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/,
                message: 'Invalid email format',
              },
            })}
          />
          {errors.email && <p className="text-danger">{errors.email.message}</p>}
        </div>

        <div className="mb-3">
          <label htmlFor="password" className="form-label">Password</label>
          <input
            type="password"
            className="form-control"
            id="password"
            {...register('password', {
              required: 'Password is required',
              minLength: { value: 8, message: 'Password must have at least 8 characters' },
            })}
          />
          {errors.password && <p className="text-danger">{errors.password.message}</p>}
        </div>

        <div className="mb-3">
          <label htmlFor="address" className="form-label">Address</label>
          <input
            type="text"
            className="form-control"
            id="address"
            {...register('address', { required: 'Address is required' })}
          />
          {errors.address && <p className="text-danger">{errors.address.message}</p>}
        </div>

        <div className="mb-3">
          <label htmlFor="adharNumber" className="form-label">Aadhar Number</label>
          <input
            type="number"
            className="form-control"
            id="adharNumber"
            {...register('adharNumber', {
              required: 'Aadhar number is required',
              pattern: {
                value: /^\d{12}$/,
                message: 'Aadhar number must be 12 digits',
              },
            })}
          />
          {errors.adharNumber && <p className="text-danger">{errors.adharNumber.message}</p>}
        </div>

        <div className="mb-3">
          <label htmlFor="drivingLicenseNo" className="form-label">Driving License No.</label>
          <input
            type="text"
            className="form-control"
            id="drivingLicenseNo"
            {...register('drivingLicenseNo', { required: 'Driving License number is required' })}
          />
          {errors.drivingLicenseNo && <p className="text-danger">{errors.drivingLicenseNo.message}</p>}
        </div>

        <div className="mb-3">
          <label htmlFor="contact" className="form-label">Contact Number</label>
          <input
            type="number"
            className="form-control"
            id="contact"
            {...register('contact', {
              required: 'Contact number is required',
              pattern: {
                value: /^\d{10}$/,
                message: 'Contact number must be 10 digits',
              },
            })}
          />
          {errors.contact && <p className="text-danger">{errors.contact.message}</p>}
        </div>

        <div className="mb-3">
          <label htmlFor="cityId" className="form-label">City</label>
          <select
            className="form-control"
            id="cityId"
            {...register('cityId', { required: 'City selection is required' })}
          >
            <option value="">Select City</option>
            {cities.map((city) => (
              <option key={city.cityId} value={city.cityId}>
                {city.cityName}
              </option>
            ))}
          </select>
          {errors.cityId && <p className="text-danger">{errors.cityId.message}</p>}
        </div>

        <button type="submit" className="btn btn-primary">
          Register
        </button>
      </form>
    </div>
  );
};

export default CustomerRegister;

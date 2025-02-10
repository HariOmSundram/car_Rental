import React from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form"; // ✅ Import react-hook-form
import axios from "axios";
import { login } from "../../redux/slices/authSlice";

const Login = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // ✅ Initialize react-hook-form
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  // ✅ Handle form submission
  const onSubmit = async (data) => {
    try {
      const response = await axios.post(
        "http://localhost:9000/login",
        data
      );

      console.log("API Response:", response.data);

      const user = response.data;
      dispatch(login({ user })); // ✅ Store user in Redux

      // ✅ Navigate based on role
      if (user.role?.roleName === "Admin") {
        navigate("/dashboard/admin");
      } else if (user.role?.roleName === "Customer") {
        navigate("/dashboard/customer/view-cars");
      } else if (user.role?.roleName === "Agency") {
        navigate("/dashboard/owner");
      } else {
        navigate("/");
      }
    } catch (error) {
      console.error("Login Error:", error);
      alert("Invalid email or password. Please try again.");
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow">
            <div className="card-body">
              <h2 className="card-title text-center">Login</h2>
              
              {/* ✅ Hook Form */}
              <form onSubmit={handleSubmit(onSubmit)}>
                {/* Email Field */}
                <div className="mb-3">
                  <label className="form-label">Email</label>
                  <input
                    type="email"
                    className="form-control"
                    {...register("email", {
                      required: "Email is required",
                      pattern: {
                        value: /^[A-Za-z0-9_.-]+@gmail\.com$/,
                        message: "Email is invalid (must be @gmail.com)",
                      },
                    })}
                  />
                  {errors.email && <p className="text-danger">{errors.email.message}</p>}
                </div>

                {/* Password Field */}
                <div className="mb-3">
                  <label className="form-label">Password</label>
                  <input
                    type="password"
                    className="form-control"
                    {...register("password", {
                      required: "Password is required",
                      pattern: {
                        value: /^[A-Za-z0-9*%$_.-]{8,18}$/,
                        message: "Password must be 8-12 characters long",
                      },
                    })}
                  />
                  {errors.password && <p className="text-danger">{errors.password.message}</p>}
                </div>

                {/* Submit Button */}
                <button type="submit" className="btn btn-primary w-100">
                  Login
                </button>
              </form>

            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;

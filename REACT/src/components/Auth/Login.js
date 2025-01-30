import React from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useForm } from "react-hook-form";
import { login } from "../../redux/slices/authSlice";

const Login = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
    setError,
  } = useForm({ mode: "onChange" });

  const onSubmit = async (data) => {
    try {
      const response = await axios.post("http://localhost:8080/login", {
        email: data.email,
        password: data.password,
      });

      const { user, role } = response.data;

      dispatch(login({ user, role }));

      switch (role.roleName) {
        case "Admin":
          navigate("/dashboard/admin");
          break;
        case "Customer":
          navigate("/dashboard/customer");
          break;
        case "Agency":
          navigate("/dashboard/owner");
          break;
        default:
          navigate("/");
          break;
      }
    } catch (error) {
      if (error.response) {
        const errorData = error.response.data;
        if (error.response.status === 401) {
          setError(
            "password",
            { type: "manual", message: "Invalid password" },
            { shouldFocus: true }
          );
          if (errorData.message === "User not found") {
            setError(
              "email",
              { type: "manual", message: "User not found" },
              { shouldFocus: true }
            );
          }
        } else {
          setError("email", {
            type: "manual",
            message: "An unexpected error occurred",
          });
        }
      } else {
        setError("email", {
          type: "manual",
          message: "Network error. Please check your connection.",
        });
      }
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow">
            <div className="card-body">
              <h2 className="card-title text-center">Login</h2>
              <form onSubmit={handleSubmit(onSubmit)}>
                <div className="mb-3">
                  <label htmlFor="email" className="form-label">
                    Email
                  </label>
                  <input
                    type="email"
                    className="form-control"
                    id="email"
                    placeholder="Your Email"
                    {...register("email", {
                      required: "Email is required",
                      pattern: {
                        value: /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/,
                        message: "Invalid email format",
                      },
                    })}
                  />
                  {errors.email && (
                    <p className="text-danger">{errors.email.message}</p>
                  )}
                </div>
                <div className="mb-3">
                  <label htmlFor="password" className="form-label">
                    Password
                  </label>
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    placeholder="Your Password"
                    {...register("password", {
                      required: "Password is required",
                      minLength: {
                        value: 8,
                        message: "Password must be at least 8 characters long",
                      },
                    })}
                  />
                  {errors.password && (
                    <p className="text-danger">{errors.password.message}</p>
                  )}
                </div>
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

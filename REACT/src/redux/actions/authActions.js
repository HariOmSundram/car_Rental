// authActions.js

// Action to login
export const login = (userData, token) => {
    return (dispatch) => {
      // Save the user data and token in localStorage (or sessionStorage)
      localStorage.setItem("user", JSON.stringify(userData));
      localStorage.setItem("token", token);
  
      // Dispatch LOGIN action to update the Redux store
      dispatch({
        type: "LOGIN",
        payload: {
          user: userData,
          token: token,
        },
      });
    };
  };
  
  // Action to logout
  export const logout = () => {
    return (dispatch) => {
      // Remove user and token from localStorage
      localStorage.removeItem("user");
      localStorage.removeItem("token");
  
      // Dispatch LOGOUT action to reset Redux store state
      dispatch({
        type: "LOGOUT",
      });
    };
  };
  
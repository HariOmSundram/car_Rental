// authReducer.js

const initialState = {
    isAuthenticated: false,
    user: null,
    token: null,
  };
  
  // Check if user data and token exist in localStorage and set the initial state accordingly
  const storedUser = localStorage.getItem("user");
  const storedToken = localStorage.getItem("token");
  
  if (storedUser && storedToken) {
    initialState.isAuthenticated = true;
    initialState.user = JSON.parse(storedUser);
    initialState.token = storedToken;
  }
  
  export default function authReducer(state = initialState, action) {
    switch (action.type) {
      case "LOGIN":
        return {
          ...state,
          isAuthenticated: true,
          user: action.payload.user,
          token: action.payload.token,
        };
      case "LOGOUT":
        return {
          ...state,
          isAuthenticated: false,
          user: null,
          token: null,
        };
      default:
        return state;
    }
  }
  
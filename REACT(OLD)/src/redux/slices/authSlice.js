import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  user: JSON.parse(sessionStorage.getItem("user")) || null,
  isAuthenticated: !!sessionStorage.getItem("user"),
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    login(state, action) {
      state.user = action.payload.user;
      state.isAuthenticated = true;
    },
    logout(state) {
      state.user = null;
      state.isAuthenticated = false;
      sessionStorage.removeItem("user"); // ✅ Clear session on logout
    },
  },
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;

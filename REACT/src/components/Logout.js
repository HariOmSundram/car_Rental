import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom"
import { logout } from "../redux/slices/authSlice";

export default function Logout() {
    console.log("logging out")
    const navigate = useNavigate();
    const dispatch = useDispatch();
    dispatch(logout())
    localStorage.clear();
    navigate("/")
}
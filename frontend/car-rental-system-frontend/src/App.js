import { Routes, Route } from "react-router-dom";
import Header from "./NavbarComponent/Header";
import AdminRegisterForm from "./UserComponent/AdminRegisterForm";
import UserLoginForm from "./UserComponent/UserLoginForm";
import UserRegister from "./UserComponent/UserRegister";
import AboutUs from "./PageComponent/AboutUs";
import ContactUs from "./PageComponent/ContactUs";
import HomePage from "./PageComponent/HomePage";
import AddVariantForm from "./CarComponent/AddVariantForm";
import AddCompanyForm from "./CompanyComponent/AddCompanyForm";
import ViewVariantDetail from "./CarComponent/ViewVariantDetail";
import ViewAllCustomers from "./UserComponent/ViewAllCustomers";
import UpdateVariantForm from "./CarComponent/UpdateVariantForm";
import ViewVariants from "./CarComponent/ViewVariants";
import ViewVehicles from "./CarComponent/ViewVehicles";
import AllBookings from "./BookingComponent/AllBookings";
import ViewCustomerBooking from "./BookingComponent/ViewCustomerBooking";
import UserProfile from "./UserComponent/UserProfile";
import AddDrivingLicense from "./UserComponent/AddDrivingLicense";
import MyBooking from "./BookingComponent/MyBooking";
import BookingPayment from "./BookingComponent/BookingPayment";

function App() {
  return (
    <div>
      <Header />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/user/admin/register" element={<AdminRegisterForm />} />
        <Route path="/user/login" element={<UserLoginForm />} />
        <Route path="/user/customer/register" element={<UserRegister />} />
        <Route path="/aboutus" element={<AboutUs />} />
        <Route path="/contactus" element={<ContactUs />} />
        <Route path="/admin/variant/add" element={<AddVariantForm />} />
        <Route path="/admin/company/add" element={<AddCompanyForm />} />
        <Route path="/car/variant/detail" element={<ViewVariantDetail />} />
        <Route
          path="/car/variant/:variantId/detail"
          element={<ViewVariantDetail />}
        />
        <Route path="/admin/customer/all" element={<ViewAllCustomers />} />
        <Route path="/admin/variant/update" element={<UpdateVariantForm />} />
        <Route path="/admin/variant/all" element={<ViewVariants />} />
        <Route path="/admin/vehicle/details" element={<ViewVehicles />} />
        <Route path="/admin/customer/bookings" element={<AllBookings />} />
        <Route
          path="/customer/vehicle/booking/details"
          element={<ViewCustomerBooking />}
        />
        <Route path="/user/profile/detail" element={<UserProfile />} />
        <Route
          path="/customer/driving-license/add"
          element={<AddDrivingLicense />}
        />
        <Route path="/customer/bookings" element={<MyBooking />} />
        <Route path="/customer/booking/payment" element={<BookingPayment />} />
      </Routes>
    </div>
  );
}

export default App;

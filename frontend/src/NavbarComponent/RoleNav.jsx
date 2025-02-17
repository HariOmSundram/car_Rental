import AdminHeader from "./AdminHeader";
import HeaderCustomer from "./HeaderCustomer";
import NormalHeader from "./NormalHeader";

const RoleNav = () => {
  const customer = JSON.parse(sessionStorage.getItem("active-customer"));
  const admin = JSON.parse(sessionStorage.getItem("active-admin"));

  if (admin != null) {
    return <AdminHeader />;
  } else if (customer != null) {
    return <HeaderCustomer />;
  } else {
    return <NormalHeader />;
  }
};

export default RoleNav;

import { Outlet } from "react-router-dom";
import Footer from "../pages/Shared/Footer";
import Header from "../pages/Shared/Header";

const DashboardLayout = () => {
  return (
    <div className="flex flex-col min-h-screen">
      <Header />
      <div className="flex-grow px-20">
      <Outlet />
      </div>
      <Footer />
    </div>
  )
};

export default DashboardLayout;

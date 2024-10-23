import DashboardLayout from "./Layout/DashboardLayout";
import DashboardSidebar from "./pages/Dashboard/DashboardSidebar";

const App = () => {
  return (
    <>
      <div className="flex">
        <div className="w-2/12">
          <DashboardSidebar />
        </div>
        <div className="flex-grow">
          <DashboardLayout />
        </div>
      </div>
    </>
  );
};

export default App;

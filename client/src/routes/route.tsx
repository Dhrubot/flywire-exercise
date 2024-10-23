import { createBrowserRouter, Navigate } from "react-router-dom";
import App from "../App";
import AllEmployees from "../pages/Dashboard/AllEmployees";
import AddEmployee from "../pages/Dashboard/AddEmployee";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/dashboard/all-employees",
        element: <AllEmployees />,
      },
      {
        path: "/dashboard/add-employee",
        element: <AddEmployee />,
      },
      {
        index: true, 
        element: <Navigate to="/dashboard/all-employees" />,
      },
    ],
  },
]);

export default router;

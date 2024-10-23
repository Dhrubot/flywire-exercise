import { Link } from "react-router-dom";

const DashboardSidebar = () => {

  return (
    <div className="w-2/12 min-h-screen fixed bg-secondaryBg flex flex-col justify-between pt-20 pb-20 items-center">
      <div>
        <Link to="/" className="text-2xl font-mono flex justify-center items-center">
          <span className="text-primaryFont font-extrabold">EMS</span>
        </Link>
        <ul className="menu p-0 pt-10 text-secondaryColor font-bold">
            <li className="text-base hover:text-primaryFont">
            <Link to="/dashboard/all-employees" className="hover:bg-transparent focus:bg-transparent focus:text-primaryFont">
              <p className="text-lg leading-10">All Employees</p>
            </Link>
          </li>

          <li className="text-base hover:text-primaryFont">
            <Link to="/dashboard/add-employee" className="hover:bg-transparent focus:bg-transparent focus:text-primaryFont">
              <p className="text-lg leading-10">Add Employee</p>
            </Link>
          </li>
        </ul>
      </div>

      <div>
        <div className="dropdown dropdown-end">
          <div
            tabIndex={0}
            role="button"
            className="btn btn-ghost btn-circle avatar"
          >
            <div className="rounded-full">
              <img
                alt="Tailwind CSS Navbar component"
                src="https://i.ibb.co.com/W6PdY6H/png-transparent-computer-icons-management-admin-silhouette-black-and-white-neck-thumbnail.png"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DashboardSidebar;

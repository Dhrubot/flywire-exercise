import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import SectionTitle from "../../components/SectionTitle";
import { FaTrashAlt } from "react-icons/fa";
import useAxiosPublic from "../../Hooks/useAxios";
import { TEmployeeTypes } from "../../types/employees.type";
import Loader from "../../components/Loader";
import Swal from "sweetalert2";

const AllEmployees = () => {
  const axiosPublic = useAxiosPublic();
  const queryClient = useQueryClient();

  const { data: allActiveEmployeesData = [], isPending } = useQuery({
    queryKey: ["allActiveEmployees"],
    queryFn: async () => {
      const res = await axiosPublic.get("/active");
      return res.data;
    },
  });

  const employeeIdToNameMap = Object.fromEntries(
    allActiveEmployeesData.map((employee: TEmployeeTypes) => [
      employee.id,
      employee.name,
    ])
  );

  const deleteEmployeeMutation = useMutation({
    mutationFn: async (id: number) => {
      await axiosPublic.put(`${id}`);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({queryKey: ['allActiveEmployees']});
    },
    onError: (error) => {
      console.error("Error deleting employee: ", error);
    }
  });

  const handleDelete = (id: number) => {
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!"
    }).then((result) => {
      if (result.isConfirmed) {
        deleteEmployeeMutation.mutate(id);
        Swal.fire("Deleted!", "Employee has been deleted.", "success");
      }
    });
  };

  if(isPending){
    return <Loader size="160px" />
  }

  return (
    <div className="pt-20">
      <SectionTitle title="All Employees" />
      <div className="overflow-x-auto">
        <table className="table text-center">
          <thead>
            <tr className="text-base">
              <th>
                <label>#</label>
              </th>
              <th className="text-left">Name</th>
              <th>Id No.</th>
              <th>Position</th>
              <th>Hire-Date</th>
              <th>Reporting to</th>
              <th>Delete Employee</th>
            </tr>
          </thead>
          <tbody>
            {allActiveEmployeesData?.map(
              (employee: TEmployeeTypes, index: number) => (
                <tr key={index}>
                  <th>
                    <label>{index + 1}</label>
                  </th>
                  <td>
                    <div className="flex items-center gap-3">
                      <div>
                        <div className="font-bold">{employee.name}</div>
                      </div>
                    </div>
                  </td>
                  <td>{employee.id}</td>
                  <td>{employee.position}</td>
                  <td>{employee.hireDate}</td>
                  <td>
                    {employee.directReports.length === 0 ? (
                      <p className="font-semibold text-primaryFont">N/A</p>
                    ) : (
                      employee.directReports.map((reportId) => (
                        <p
                          key={reportId}
                          className="font-semibold text-secondaryColor"
                        >
                          {employeeIdToNameMap[reportId]}
                        </p>
                      ))
                    )}
                  </td>
                  <td>
                    <button onClick={() => handleDelete(employee.id)}>
                      <FaTrashAlt
                        size="24"
                        className="text-primaryFont hover:text-secondaryColor"
                      />
                    </button>
                  </td>
                </tr>
              )
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AllEmployees;

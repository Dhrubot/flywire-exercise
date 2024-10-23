import { FieldValues, SubmitHandler, useForm } from "react-hook-form";
import SectionTitle from "../../components/SectionTitle";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import useAxiosPublic from "../../Hooks/useAxios";
import { TEmployeeTypes } from "../../types/employees.type";

const AddEmployee = () => {
  const axiosPublic = useAxiosPublic();
  const queryClient = useQueryClient();

  const { data: allActiveEmployeesData = [], isPending } = useQuery({
    queryKey: ["allActiveEmployeesData"],
    queryFn: async () => {
      const res = await axiosPublic.get("/active");
      return res.data;
    },
  });

  console.log(allActiveEmployeesData);

  const employeeIdToNameMap = Object.fromEntries(
    allActiveEmployeesData.map((employee: TEmployeeTypes) => [
      employee.id,
      employee.name,
    ])
  );

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
    control,
  } = useForm();

  const onSubmit: SubmitHandler<FieldValues> = async (data) => {
    console.log(data);
  };

  return (
    <div className="pt-20">
      <SectionTitle title="Create an Employee" />
      <div className="hero">
        <div className="hero-content flex-col lg:flex-row-reverse pb-20">
          <div className="card w-[600px] shrink-0 shadow-2xl shadow-primaryFont">
            <form
              className="card-body pb-20 bg-base-200"
              onSubmit={handleSubmit(onSubmit)}
            >
              <div className="flex gap-5 pb-5 items-center">
                <label className="label">
                  <span className="text-lg">Reporting to: </span>
                </label>
                <select
                  className="select select-bordered border-primaryFont w-full max-w-xs"
                  {...register("room", { required: true })}
                  defaultValue=""
                >
                  <option disabled value="">
                    Select a room
                  </option>
                  {allActiveEmployeesData?.map((employee: TEmployeeTypes, index: number) => (
                    <option key={index} value={employee.id}>
                      {employee.name}
                    </option>
                  ))}
                </select>
              </div>
              {errors.room && (
                <span className="text-red-600 px-2">Please select a room</span>
              )}

              <div className="form-control mt-6">
                <button
                  type="submit"
                  className=" btn bg-primaryFont rounded text-black hover:bg-secondaryColor font-bold"
                >
                  Add Room
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddEmployee;

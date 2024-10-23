import { RingLoader } from "react-spinners";

const Loader = ({size}: {size: string}) => {
  return (
    <div className="w-full min-h-screen grow flex justify-center items-center flex-col shadow-sm rounded-xl dark:bg-gray-800 dark:border-gray-700 dark:shadow-slate-700/[.7]">
      <div className="flex flex-auto flex-col justify-center items-center p-4 md:p-5">
        <div className="flex justify-center">
          <RingLoader color="#147cd1" size={size} />
        </div>
      </div>
    </div>
  );
};

export default Loader;

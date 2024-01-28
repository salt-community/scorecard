"use client";

import { CardHeader } from "@nextui-org/react";

const Grading = () => {
  return (
    <div className="p-2">
      <CardHeader>
        <h4 className="font-bold text-large">Grading system</h4>
      </CardHeader>
      <ul className="w-full flex flex-row ">
        <li className=" border-solid border-2 border-r-0 flex-1 text-center text-sm px-2 py-1 bg-grading1">
          0-20
        </li>
        <li className=" border-solid border-2 flex-1 border-r-0 text-center text-sm px-2 py-1 bg-grading2">
          21-40
        </li>
        <li className=" border-solid border-2 flex-1 border-r-0 text-center text-sm px-2 py-1 bg-grading3">
          41-60
        </li>
        <li className=" border-solid border-2 flex-1 border-r-0 text-center text-sm px-2 py-1 bg-grading4">
          61-80
        </li>
        <li className=" border-solid border-2 flex-1 text-center text-sm px-2 py-1 bg-grading5">
          81-100
        </li>
      </ul>
    </div>
  );
};

export default Grading;

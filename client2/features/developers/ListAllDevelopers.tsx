import React, { useEffect } from "react";

import { Card } from "@material-tailwind/react";
import Link from "next/link";
import { Developer, getAllDevelopers } from "@/server";
import { headers } from "next/headers";

export const ListAllDevelopers = ({
  setDevelopers,
  developers,
}: {
  setDevelopers: Function;
  developers: Developer[];
}) => {
  useEffect(() => {
    const response = getAllDevelopers();
    setDevelopers(response);
  }, []);

  return (
    <>
      <Card className="p-4 shadow-none" placeholder={undefined}>
        <div className="overflow-y-auto">
          <table className="w-full text-sm text-left text-black">
            <thead className="border text-xs text-black uppercase bg-slate-300">
              <tr key={'headers'} className=" text-text text-center">
                <th scope="col" className="border px-6 py-3 w-2/5">
                  Name
                </th>
                <th scope="col" className="border px-6 py-3 w-2/5">
                  Email
                </th>
                <th scope="col" className="border px-6 py-3 w-2/5">
                  Bootcamp
                </th>
                <th scope="col" className="border px-6 py-3 ">
                  Edit
                </th>
                <th scope="col" className="border px-6 py-3 ">
                  Delete
                </th>
              </tr>
              {developers.map((developer, index) => (
                <tr
                  key={index}
                  className="even:bg-white odd:bg-gray-100"
                >
                  <td className="border px-6 py-4">
                    <Link href={`/developer/${developer.developerId}`}>
                      {developer.firstName} {developer.lastName}
                    </Link>
                  </td>
                  <td className="border px-6 py-4">{developer.emailAddress}</td>
                  <td className="border px-6 py-4">
                    {developer.bootcampCourse}
                  </td>
                </tr>
              ))}
            </thead>
          </table>
        </div>
      </Card>
    </>
  );
};

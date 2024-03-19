import React, { useEffect, useState } from "react";

import Link from "next/link";
import { Developer } from "@/server/developer/route";
import { Card, CardBody, CardHeader } from "@nextui-org/react";

export const ListAllDevelopers = ({
  developers,
}: {
  developers: Developer[];
}) => {
  return (
    <>
      <Card className="relative p-4 shadow-none">
        <div className="text-center mb-4 font-extrabold	">
          <CardHeader>
            <h2>List of Developers</h2>{" "}
          </CardHeader>
        </div>
        <CardBody>
          <div className="overflow-y-auto flex justify-center">
            <table className="w-[80%] items-center text-sm text-left text-black">
              <thead className="border text-xs text-black uppercase bg-slate-300">
                <tr className=" text-text text-center">
                  <th scope="col" className="border px-6 py-3 w-2/5">
                    Name
                  </th>
                  <th scope="col" className="border px-6 py-3 w-2/5">
                    Email
                  </th>
                  <th scope="col" className="border px-6 py-3 w-2/5">
                    Bootcamp
                  </th>
                </tr>

                {developers.map((developer) => (
                  <tr
                    key={developer.developerId}
                    className="even:bg-white odd:bg-gray-100"
                  >
                    <td className="border px-6 py-4">
                      <Link href={`/developer/${developer.developerId}`}>
                        {developer.firstName} {developer.lastName}
                      </Link>
                    </td>
                    <td className="border px-6 py-4">
                      {developer.emailAddress}
                    </td>
                    <td className="border px-6 py-4">
                      {developer.bootcampCourse}
                    </td>
                  </tr>
                ))}
              </thead>
            </table>
          </div>
        </CardBody>
      </Card>
    </>
  );
};

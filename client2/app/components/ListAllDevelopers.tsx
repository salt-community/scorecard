import React, { useEffect, useState } from "react";

import { Button, Card } from "@material-tailwind/react";
import Link from "next/link";

type Developer = {
  developerId: string;
  firstName: string;
  lastName: string;
  emailAddress: string;
  bootcampCourse: string;
};

export const ListAllDevelopers = () => {
  const [developers, setDevelopers] = useState<Developer[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/v2/developers`, {
          cache: "no-cache",
          method: "GET",
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error("Failed to fetch data");
        }

        const data = await response.json();
        const listOfDevelopers = data.developerResponseList as Developer[]
        setDevelopers(listOfDevelopers);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);


  return (
    <>
      <Card className="p-4 shadow-none" placeholder={undefined}>
        <div className="overflow-y-auto">
          <table className="w-full text-sm text-left text-black">
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
                <th scope="col" className="border px-6 py-3 ">
                  Edit
                </th>
                <th scope="col" className="border px-6 py-3 ">
                  Delete
                </th>
              </tr>

              {developers.map((developer) => (
                <tr
                  key={developer.developerId}
                  className="even:bg-white odd:bg-gray-100"
                >
                  <td className="border px-6 py-4">
                    <Link href={`/developer/${developer.developerId}`}>{developer.firstName} {developer.lastName}</Link>
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

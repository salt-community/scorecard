"use client";
import React from "react";
import Link from "next/link";

import { Button, Card } from "@material-tailwind/react";
import { RadarGraphicData } from "@/app/types";

type saltieAdmin = {
  id: string;
  name: string;
  radarGraph: RadarGraphicData[];
};

type Props = {
  salties: saltieAdmin[];
};

const AllDevelopers = ({ salties }: Props) => {
  return (
    <Card className="p-4" placeholder={undefined}>
      <div className="overflow-y-auto">
        <table className="w-full text-sm text-left text-black mt-10 ">
          <thead className="border text-xs text-black uppercase bg-slate-300 dark:bg-slate-300 dark:text-black">
            <tr className=" bg-accent text-text text-center">
              <th scope="col" className="border px-2 py-3 w-4/12">
                Name
              </th>
              <th scope="col" className="border px-2 py-3 w-1/12 min-w-24">
                Frontend
              </th>
              <th scope="col" className="border px-2 py-3 w-1/12 min-w-24">
                Backend
              </th>
              <th scope="col" className="border px-2 py-3 w-1/12 min-w-24">
                Charismatic
              </th>
              <th scope="col" className="border px-2 py-3 w-1/12 min-w-24">
                Teamwork
              </th>
              <th scope="col" className="border px-2 py-3 w-1/12 min-w-24">
                Design
              </th>
              <th scope="col" className="border px-2 py-3 w-1/12 min-w-24">
                Management
              </th>
              <th scope="col" className="border px-2 py-3 w-2/12">
                Action
              </th>
            </tr>
            {salties?.map((developer) => {
              return (
                <tr
                  key={developer.id}
                  className="even:bg-white odd:bg-gray-100 text-center"
                >
                  <td className="border px-2 py-4">{developer.name}</td>
                  <td className="border px-2 py-4">
                    {Math.round((developer.radarGraph[0].score * 10) / 10)}
                  </td>
                  <td className="border px-2 py-4">
                    {Math.round((developer.radarGraph[1].score * 10) / 10)}
                  </td>
                  <td className="border px-2 py-4">
                    {Math.round((developer.radarGraph[2].score * 10) / 10)}
                  </td>
                  <td className="border px-2 py-4">
                    {Math.round((developer.radarGraph[3].score * 10) / 10)}
                  </td>
                  <td className="border px-2 py-4">
                    {Math.round((developer.radarGraph[4].score * 10) / 10)}
                  </td>
                  <td className="border px-2 py-4">
                    {Math.round((developer.radarGraph[5].score * 10) / 10)}
                  </td>
                  <td className="border px-2 py-4 text-center">
                    <Link
                      href={`/dashboard/scoreboard/${developer.id}`}
                      className=""
                    >
                      <Button
                        className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
                        placeholder={undefined}
                      >
                        Add Score
                      </Button>
                    </Link>
                  </td>
                </tr>
              );
            })}
          </thead>
        </table>
      </div>
    </Card>
  );
};

export default AllDevelopers;

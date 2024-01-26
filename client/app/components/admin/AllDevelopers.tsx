"use client";
import React from "react";
import Link from "next/link";

import { Button, Card, Typography } from "@material-tailwind/react";

type developerInList = {
  id: string;
  name: string;
  profilePicture: string;
  standoutIntro: string;
};

type Props = {
  developers: developerInList[];
};

const AllDevelopers = ({ developers }: Props) => {
  return (
    <Card className="p-4" placeholder={undefined}>
      <Typography variant="h5" color="blue-gray" placeholder={undefined}>
        All developers
      </Typography>
      <div className="overflow-y-auto">
        <table className="w-full text-sm text-left text-black mt-10 ">
          <thead className="border text-xs text-black uppercase bg-slate-300 dark:bg-slate-300 dark:text-black">
            <tr className=" bg-accent text-text">
              <th scope="col" className="border px-6 py-3 w-2/5">
                Name
              </th>
              <th scope="col" className="border px-6 py-3 text-center">
                Action
              </th>
            </tr>
            {developers?.map((developer) => {
              return (
                <tr
                  key={developer.id}
                  className="even:bg-white odd:bg-gray-100"
                >
                  <td className="border px-6 py-4">
                    <Link
                      href={`/dashboard/developers/${developer.id}`}
                      className=" underline hover:text-primary"
                    >
                      {developer.name}
                    </Link>
                  </td>
                  <td className="border px-6 py-4 text-center">
                    <Link
                      href={`/filteredfigures/review/${developer.id}`}
                      passHref
                    >
                      <Button
                        className="bg-accent2 hover:bg-bannerColor2 text-white font-bold py-2 px-4 rounded"
                        placeholder={undefined}
                      >
                        delete
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

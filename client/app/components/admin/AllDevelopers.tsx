"use client";
import React, { useContext, useEffect, useState } from "react";
import Link from "next/link";

import {
  Popover,
  PopoverHandler,
  PopoverContent,
  Button,
  Card,
  Typography,
  Rating,
} from "@material-tailwind/react";


type developerInList = {
    id: string;
    name: string;
    profilePicture: string;
    standoutIntro: string;
  };

type Props = {
  developers: developerInList[]
}

const AllDevelopers = ({developers}: Props) => {
  


  return (
    <Card className="absolute right-0 top-[6rem] w-full max-w-[calc(100vw-19rem)]  h-[calc(100vh-6rem)] p-4 shadow-xl shadow-blue-gray-900/5 border-solid border-2 ">
      <Typography variant="h5" color="blue-gray">
        All developers 
      </Typography>
      <div className="overflow-y-auto">
        <table className="w-full text-sm text-left text-black mt-10 ">
          <thead className="border text-xs text-black uppercase bg-slate-300 dark:bg-slate-300 dark:text-black">
            <tr className="bg-primary text-text">
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
                      href={`/filteredfigures/review/${developer.id}`}
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
                     <Button className="bg-bannerColor1 hover:bg-bannerColor2 text-white font-bold py-2 px-4 rounded">
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
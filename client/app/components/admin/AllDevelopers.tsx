"use client";
import React from "react";
import Link from "next/link";
import { Button, Card } from "@material-tailwind/react";
import { httpDeleteDeveloperById } from "@/app/api/request";
import { useRouter } from "next/navigation";

type developerAdmin = {
  id: string;
  name: string;
  email: string;
  phoneNumber: string;
  role: string;
};

type Props = {
  developers: developerAdmin[];
};

export const AllDevelopers = ({ developers }: Props) => {
  const router = useRouter();
  const deleteHandler = async (id: string) => {
    const response = await httpDeleteDeveloperById(id);
    if (response.status === 204) {
      // deleteScore(searchedScore.id);
      // deleteSearchedScore();
      router.push("/dashboard/developers");
    } else {
      throw new Error("delete is canceled!!");
    }
  };

  return (
    <Card className="p-4" placeholder={undefined}>
      <div className="overflow-y-auto">
        <table className="w-full text-sm text-left text-black mt-10 ">
          <thead className="border text-xs text-black uppercase bg-slate-300 dark:bg-slate-300 dark:text-black">
            <tr className=" bg-accent text-text text-center">
              <th scope="col" className="border px-6 py-3 w-2/5">
                Name
              </th>
              <th scope="col" className="border px-6 py-3 w-2/5">
                Email
              </th>
              <th scope="col" className="border px-6 py-3 w-2/5">
                Phone Number
              </th>
              <th scope="col" className="border px-6 py-3 w-2/5">
                Role
              </th>
              <th scope="col" className="border px-6 py-3 ">
                Edit
              </th>
              <th scope="col" className="border px-6 py-3 ">
                Delete
              </th>
            </tr>
            {developers.length > 0
              ? developers.map((developer) => {
                  return (
                    <tr
                      key={developer.id}
                      className="even:bg-white odd:bg-gray-100"
                    >
                      <td className="border px-6 py-4">{developer.name}</td>
                      <td className="border px-6 py-4">{developer.email}</td>
                      <td className="border px-6 py-4">
                        {developer.phoneNumber}
                      </td>
                      <td className="border px-6 py-4">{developer.role}</td>
                      <td className="border px-6 py-4 text-center">
                        <Link href={`/dashboard/developers/${developer.id}`}>
                          <Button
                            className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
                            placeholder={undefined}
                          >
                            Edit
                          </Button>
                        </Link>
                      </td>
                      <td className="border px-6 py-4 text-center">
                        <Button
                          className="bg-red-500 hover:bg-accent text-white font-bold py-2 px-4 rounded"
                          placeholder={undefined}
                          type="submit"
                          onClick={() => deleteHandler(developer.id)}
                        >
                          Delete
                        </Button>
                      </td>
                    </tr>
                  );
                })
              : null}
          </thead>
        </table>
      </div>
    </Card>
  );
};

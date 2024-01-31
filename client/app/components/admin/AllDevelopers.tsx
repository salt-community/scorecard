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

type AllDevelopersProps = {
  developers: developerAdmin[];
};

export const AllDevelopers = ({ developers }: AllDevelopersProps) => {
  const router = useRouter();
  const deleteHandler = async (id: string) => {
    const response = await httpDeleteDeveloperById(id);
    if (response.status === 204) {
      router.push("/dashboard/developers");
    } else {
      throw new Error("delete is canceled!!");
    }
  };

  return (
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
            {developers?.map(({ id, name, email, phoneNumber, role }) => {
              return (
                <tr key={id} className="even:bg-white odd:bg-gray-100">
                  <td className="border px-6 py-4">{name}</td>
                  <td className="border px-6 py-4">{email}</td>
                  <td className="border px-6 py-4">{phoneNumber}</td>
                  <td className="border px-6 py-4">{role}</td>
                  <td className="border px-6 py-4 text-center">
                    <Link href={`/dashboard/developers/${id}`}>
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
                      onClick={() => deleteHandler(id)}
                    >
                      Delete
                    </Button>
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

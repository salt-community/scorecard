import { Card } from "@nextui-org/react";
import React, { useEffect } from "react";

export type Assignment = {
  assignmentId: string;
  title: string;
  score: number;
  description: string;
  category: string;
  accountId: string;
};

export const ListAssignmentsForAccount = ({
  accountId,
  setAssignments,
  assignments,
}: {
  accountId: string;
  setAssignments: Function;
  assignments: Assignment[];
}) => {
  useEffect(() => {
    fetchAllAssignments();
  }, []);

  const fetchAllAssignments = () => {
    fetch(`http://localhost:8080/api/v2/scorecard/${accountId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      .then((data) => setAssignments(data.assignmentResponseList))
      .catch(Error);
  };

  return (
    <>
      <Card className="p-4 shadow-none">
        <div className="overflow-y-auto">
          <table className="w-full text-sm text-left text-black">
            <thead className="border text-xs text-black uppercase bg-slate-300">
              <tr className=" text-text text-center">
                <th scope="col" className="border px-6 py-3 w-2/5">
                  Category
                </th>
                <th scope="col" className="border px-6 py-3 w-2/5">
                  Title
                </th>
                <th scope="col" className="border px-6 py-3 w-2/5">
                  Score
                </th>
                <th scope="col" className="border px-6 py-3 w-2/5">
                  Description
                </th>
              </tr>

              {assignments?.map((a) => (
                <tr
                  key={a.assignmentId}
                  className="even:bg-white odd:bg-gray-100"
                >
                  <td className="border px-6 py-4">{a.category}</td>
                  <td className="border px-6 py-4">{a.title}</td>
                  <td className="border px-6 py-4">{a.score}</td>
                  <td className="border px-6 py-4">{a.description}</td>
                </tr>
              ))}
            </thead>
          </table>
        </div>
      </Card>
    </>
  );
};

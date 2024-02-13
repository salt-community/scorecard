import React from "react";

type Assignment = {
  assignmentId: string;
  title: string;
  score: number;
  description: string;
  category: string;
  accountID: string;
};

export const ListAssignmentsForAccount = async ({
  accountId,
}: {
  accountId: string;
}) => {
  const response = await fetch(
    `http://localhost:8080/api/v2/assignments?accountId=${accountId}`,
    {
      cache: "no-cache",
      method: "GET",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    }
  ).then((res) => res.json());

  const listOfAssignments = response.assignmentResponseList as Assignment[];
  const filtered = listOfAssignments;

  return (
    <div>
      <div>List of Assignments For Account</div>
      {filtered.map((a) => (
        <div key={a.assignmentId} className="p-2 mb-2 border rounded-sm">
          <p>{a.category}</p>
          <p>{a.title}</p>
          <p>{a.score}</p>
          <p>{a.description}</p>
        </div>
      ))}
    </div>
  );
};

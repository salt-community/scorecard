import React, { ReactNode, useState } from "react";

type Assignment = {
  assignmentId: string,
  title: string,
  score: number,
  description: string,
  category:string
}


export const ListAssignmentsForAccount = ({
  accountId,
}: {
  accountId: string;
}): ReactNode => {
  const [assignments, setAssignments] = useState<Assignment[]>([]);
  const listOfAssignments = async () => {
    const response = await fetch(`http://localhost:8080/api/v2/assignments?accountId=${accountId}`, {
      method: "GET",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    });
    const data = await response.json;
  };

  return <div>ListAssignmentsForAccount</div>;
};

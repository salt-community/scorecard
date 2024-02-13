import React, { ReactNode, useState } from "react";

type AssignmentFormInfo = {
  title: string;
  score: number;
  description: string;
  category: string;
};

export const AddAssignmentForm = ({
  accountId,
}: {
  accountId: string;
}): ReactNode => {
  const [assignment, setAssignment] = useState<AssignmentFormInfo>({
    title: "",
    score: 0,
    description: "",
    category: "",
  });

  const handleInputChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = event.target;
    setAssignment({
      ...assignment,
      [name]: name === "score" ? Number(value) : value,
    });
  };

  const submitAssignment = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const payload = JSON.stringify({
      accountId,
      ...assignment,
    });
    const response = await fetch("http://localhost:8080/api/v2/assignments", {
      method: "POST",
      body: payload,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    });
    const data = response.json();
  };

  return (
    <>
      <form onSubmit={submitAssignment}>
        <label htmlFor="title">Title:</label>
        <input
          type="text"
          id="title"
          name="title"
          value={assignment.title}
          onChange={handleInputChange}
        ></input>
        <label htmlFor="score">Score:</label>
        <input
          type="number"
          id="score"
          name="score"
          value={assignment.score}
          onChange={handleInputChange}
        ></input>
        <label htmlFor="description">Description:</label>
        <input
          type="text"
          id="description"
          name="description"
          value={assignment.description}
          onChange={handleInputChange}
        ></input>
        <label htmlFor="category">Category:</label>
        <select id="category" name="category" onChange={handleInputChange}>
          <option>Select...</option>
          <option value={"Backend"}>Backend</option>
          <option value={"Frontend"}>Frontend</option>
        </select>
        <button type="submit">Submit</button>
      </form>
    </>
  );
};

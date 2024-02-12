"use client";
import React, { ReactNode, useState } from "react";

export const AddAssignmentForm = ({ accountId }: { accountId: string }):ReactNode => {
  const [title, setTitle] = useState("");
  const [score, setScore] = useState(0);
  const [description, setDescription] = useState("");
  const [category, setCategory] = useState("");

  const submitAssignment = async () => {
    const payload = JSON.stringify({
      accountId,
      title,
      score,
      description,
      category,
    });
    const response = await fetch("http://localhost:8080/api/v2/assignments", {
      method: "POST",
      body: payload,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    });
    const data = await response.json();
  };
  //do post to create assignment -> form
  //do get to recceive list of all assignments for this account
  //if we add this component to slug page we have the account id

  return
  <div>
  <h1 className=" text-red-500">RENDERING</h1>
  </div>
/*   <form onSubmit={submitAssignment}>
    <label htmlFor="assignmentTitle">Assignment title:</label>
    <input type="text" id="assignmentTitle"></input>
    <label htmlFor="assignmentScore">Assignment score:</label>
    <input type="number" id="assignmentScore"></input>
    <label htmlFor="assignmentDescription">Assignment description:</label>
    <input type="text" id="assignmentDescription"></input>
    <label htmlFor="assignmentCategory">Assignment category:</label>
    <input type="text" id="assignmentCategory"></input>
    <button type="submit">Submit</button>
  </form>; */
};

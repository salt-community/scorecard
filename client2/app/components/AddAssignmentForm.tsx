"use client";
import React, { ReactNode, useState } from "react";

export const AddAssignmentForm = ({ accountId }: { accountId: string }):ReactNode => {
  const [title, setTitle] = useState("");
  const [score, setScore] = useState<number>(0);
  const [description, setDescription] = useState("");
  const [category, setCategory] = useState("");

  const handleTitleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  e.preventDefault();
  setTitle(e.target.value)
  }

    const handleScoreChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    setScore(Number(e.target.value))
    }

    const handleDescriptionChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      e.preventDefault();
      setDescription(e.target.value)
      }

      const handleSelectCategory = (e: React.ChangeEvent<HTMLSelectElement>) => {
        e.preventDefault();
        setCategory(e.target.value)
        console.log(category)
        }

  const submitAssignment = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
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
    console.log(data)
  };

  return <>
  <form onSubmit={submitAssignment}>
    <label htmlFor="title">Title:</label>
    <input type="text" id="title" value={title} onChange={handleTitleChange}></input>
    <label htmlFor="score">Score:</label>
    <input type="number" id="score" value={score} onChange={handleScoreChange}></input>
    <label htmlFor="description">Description:</label>
    <input type="text" id="description" value={description} onChange={handleDescriptionChange}></input>
    <label htmlFor="category">Category:</label>
    <select onChange={handleSelectCategory}>
      <option value={"BACKEND"}>Backend</option>
      <option value={"FRONTEND"}>Frontend</option>
    </select>
    <button type="submit">Submit</button>
  </form>
  </>
};

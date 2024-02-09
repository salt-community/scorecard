"use client";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function RegisterAccountForm() {
  const [emailAddress, setEmailAddress] = useState("");
  const [id, setId] = useState("");
  const handleEmailChange = (e: any) => {
    e.preventDefault();
    setEmailAddress(e.target.value);
  };
  const router = useRouter();

  const submitEmail = async () => {
    const payload = JSON.stringify({ emailAddress });
    const response = await fetch("http://localhost:8080/api/v2/accounts", {
      method: "POST",
      body: payload,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    });
const data = await response.json();
setId(data.id)
  };

  const submitEmailChange = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    submitEmail();
    router.push(`assignments/${id}`)
  };

  return (
    <>
      <form onSubmit={submitEmailChange}>
        <label htmlFor="Email">Email:</label>
        <input onChange={handleEmailChange} type="Email"></input>
        <button type="submit">Submit</button>
      </form>
    </>
  );
}

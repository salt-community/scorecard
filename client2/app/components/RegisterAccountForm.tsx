"use client";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { Button, Typography } from "@material-tailwind/react";
import { Card, Input, Link } from "@nextui-org/react";

export default function RegisterAccountForm() {
  const [emailAddress, setEmailAddress] = useState("");
  const [id, setId] = useState("");

  const handleEmailChange = (event: any) => {
    event.preventDefault();
    setEmailAddress(event.target.value);
  };
  const router = useRouter();

  const submitEmail = async () => {
    const payload = JSON.stringify({ emailAddress });
    const response = await fetch("http://localhost:8080/api/v2/accounts", {
      method: "POST",
      body: payload,
      headers: {
        "Content-Type": "application/json",
      },
    });
    const data = await response.json();
    setId(data.id);
    router.push(`/${data.id}`);
  };

  const submitEmailChange = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    await submitEmail();
  };

  return (
    <div className="flex h-screen w-full flex-col items-center justify-center gap-4">
      <Card className="w-96 flex flex-col items-center h-fit py-8">
        <Link href={`/`}>
          <Typography
            variant="h2"
            className="text-accent font-bold text-5xl pb-8"
            placeholder={undefined}
          >
            Scorecard
          </Typography>
        </Link>
        <Typography
          variant="h5"
          className="text-md w-80 text-center pb-4"
          placeholder={undefined}
        >
          Register using your SALT email
        </Typography>
        <form 
        onSubmit={submitEmailChange}
        className="flex flex-col gap-4 items-center">
          <Input
            onChange={handleEmailChange}
            type="Email"
            placeholder="Email:"
            className="w-80"
          />
          <Button 
          className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded w-24 mx-auto"
           placeholder={undefined}
          type="submit">Submit</Button>
        </form>
      </Card>
    </div>
  );
}

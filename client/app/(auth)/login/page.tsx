"use client";
import React, { useState } from "react";
import { Button, Typography } from "@material-tailwind/react";
import { Card, Input, Link, Select, SelectItem } from "@nextui-org/react";
import { httpGetAccountByEmail } from "@/app/api/request";
import { Account } from "@/app/types";
import { useRouter } from "next/navigation";

const Page = () => {
  const [isNotFound, setIsNotFound] = useState<boolean | undefined>(false);
  const [isNotFoundMessage, setIsNotFoundMessage] = useState<String>();
  const [input, setInput] = useState({
    email: "",
    role: "core",
  });
  const router = useRouter();

  const handleOnChange = (event: any) => {
    const { name, value } = event.target;
    setInput({
      ...input,
      [name]: value,
    });
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const respone: Account = await httpGetAccountByEmail(input.email);
    if (respone === null || respone.role !== "core") {
      setIsNotFound(true);
      setIsNotFoundMessage(
        "No core team account found with email: " + input.email
      );
    } else {
      router.push("/dashboard");
    }
  };

  return (
    <div className="flex h-screen w-full flex-col items-center justify-center gap-4">
      <Card className=" w-96 flex flex-col items-center h-fit py-8">
        <Link href={`/`}>
          <Typography
            variant="h2"
            className="text-accent font-bold text-5xl pb-8"
            placeholder={undefined}
          >
            Scorecard
          </Typography>
        </Link>
        <form
          onSubmit={(event) => handleSubmit(event)}
          className="flex flex-col gap-4 items-center"
        >
          <Input
            name="email"
            type="email"
            onChange={handleOnChange}
            placeholder="Email:"
            isInvalid={isNotFound}
            errorMessage={isNotFoundMessage}
            className=" w-80"
          />
          <Button
            className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded w-24"
            placeholder={undefined}
            type="submit"
          >
            Log in
          </Button>
        </form>
      </Card>
      <Card className=" w-96 flex flex-col items-center h-fit py-8">
        <Typography variant="h5" className=" text-md" placeholder={undefined}>
          Don&apos;t have an account?{" "}
          <span>
            {" "}
            <Link href="/signup">Sign up</Link>
          </span>
        </Typography>
      </Card>
    </div>
  );
};

export default Page;

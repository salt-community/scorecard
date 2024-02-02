"use client";
import React, { useState } from "react";
import { Button, Typography } from "@material-tailwind/react";
import { Card, Input, Link, Select, SelectItem } from "@nextui-org/react";
import { httpCreateAccount, httpGetAccountByEmail } from "@/app/api/request";
import { Account } from "@/app/types";
import { useRouter } from "next/navigation";

const SignupPage = () => {
  const [input, setInput] = useState({
    email: "",
    name: "",
    role: "",
  });
  const router = useRouter();
  const [value, setValue] = useState<string>("");

  const validateEmail = (value: string) =>
    value.match(/^[A-Z0-9._%+-]+@appliedtechnology.se/i);

  const isInvalid = React.useMemo(() => {
    if (value === "") return false;

    return validateEmail(value) ? false : true;
  }, [value]);

  const handleOnChange = (event: any) => {
    const { name, value } = event.target;
    setInput({
      ...input,
      [name]: value,
    });
  };

  const handleSubmit = async (
    event: React.FormEvent<HTMLFormElement>,
    input: any
  ) => {
    event.preventDefault();
    if (input.email.split("@").pop() === "appliedtechnology.se") {
      input.role = "core";
    } else {
      return;
    }

    const response = await httpCreateAccount(input);
    if (response.status == 200) {
      router.push("/dashboard");
    } else if (response.status == 500) {
    } else {
      new Error("Something was wrong!");
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
        <Typography
          variant="h5"
          className=" text-md w-80 text-center"
          placeholder={undefined}
        >
          Sign up using
        </Typography>
        <Typography
          variant="h5"
          className=" text-md w-80 text-center pb-4"
          placeholder={undefined}
        >
          @appliedtechnology.se email.
        </Typography>
        <form
          onSubmit={(event) => handleSubmit(event, input)}
          className="flex flex-col gap-4 items-center"
        >
          <Input
            name="email"
            type="email"
            onChange={handleOnChange}
            placeholder="Email:"
            className=" w-80"
            isInvalid={isInvalid}
            color={isInvalid ? "danger" : "default"}
            errorMessage={isInvalid && "Please enter a valid email"}
            value={value}
            onValueChange={setValue}
          />
          <Input
            name="name"
            type="text"
            onChange={handleOnChange}
            placeholder="Name:"
            className=" w-80"
          />
          <Button
            className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded w-24"
            placeholder={undefined}
            type="submit"
          >
            Sign up
          </Button>
        </form>
      </Card>
      <Card className=" w-96 flex flex-col items-center h-fit py-8">
        <Typography variant="h5" className=" text-md" placeholder={undefined}>
          Have an account?{" "}
          <span>
            {" "}
            <Link href="/login">Log in</Link>
          </span>
        </Typography>
      </Card>
    </div>
  );
};

export default SignupPage;

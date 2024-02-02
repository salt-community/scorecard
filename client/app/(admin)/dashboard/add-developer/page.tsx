"use client";
import React, { useEffect, useState } from "react";
import { Button } from "@material-tailwind/react";
import { Input, Select, SelectItem, divider } from "@nextui-org/react";
import { httpCreateAccount, httpPostDeveloper } from "@/app/api/request";
import { useRouter } from "next/navigation";
import { useCookies } from "next-client-cookies";

const Page = () => {
  const [input, setInput] = useState({
    email: "",
    name: "",
    role: "",
    standoutIntro: "",
    phoneNumber: "",
    bootcamp: "java",
    githubUsername: "",
    linkedinUsername: "",
    codewarsUsername: "",
    selectedProjects: [] as string[],
    backgroundInformation: {
      nationalities: [],
      spokenLanguages: {},
      academic: {
        degree: "",
        major: "",
        startDate: "",
        endDate: "",
        school: "",
      },
      skills: [],
    },
  });
  const router = useRouter();
  const cookies = useCookies();

  const inputForm = (el: any) => {
    const { name, value } = el.target;
    setInput({
      ...input,
      [name]: value,
    });
  };

  const checkRole = () => {
    const role = cookies.get("salt_role");
    if (role != "core") {
      router.push("/login");
    }
  };

  const submitHandler = async (e: any, input: any) => {
    e.preventDefault();
    if (input.role == "core") {
      const response = await httpPostDeveloper(input);
      if (response.status == 200) {
        router.push("/dashboard/developers");
      } else {
        throw new Error("something went wrong!!");
      }
    } else {
      const body = {
        email: input.email,
        name: input.name,
        role: input.role,
      };
      const response = await httpCreateAccount(body);
      if (response.status == 200) {
        router.push("/dashboard/core-team");
      } else {
        throw new Error("something went wrong!!");
      }
    }
  };

  const roles = ["core", "saltie", "pgp", "consultant"];
  const bootcamps = ["java", "javascript", "dotnet"];

  useEffect(() => {
    checkRole();
  }, []);
  if (cookies.get("salt_role") != "core") {
    return <h1>Loading ...</h1>;
  } else {
    return (
      <div className="flex flex-row justify-center items-center h-full">
        <form
          id="form"
          onSubmit={(e) => submitHandler(e, input)}
          className="flex flex-col h-full justify-center gap-4"
        >
          <div className=" w-full p-4 flex flex-col gap-4">
            <Select
              name="role"
              label="Role :"
              labelPlacement="outside"
              placeholder="Please choose role..."
              onChange={inputForm}
            >
              {roles.map((roles: string) => (
                <SelectItem key={roles} value={roles}>
                  {roles}
                </SelectItem>
              ))}
            </Select>
            <div>
              {input.role == "" ? (
                <div />
              ) : (
                <div>
                  <Input
                    name="email"
                    type="email"
                    label="Email:"
                    labelPlacement="outside"
                    placeholder="Enter email"
                    color="default"
                    step={"1"}
                    onChange={inputForm}
                    className="w-72"
                  />
                  <Input
                    name="name"
                    type="text"
                    label="Name:"
                    labelPlacement="outside"
                    placeholder="Enter Name"
                    color="default"
                    step={"1"}
                    onChange={inputForm}
                    className="w-72"
                  />
                  {input.role != "core" ? (
                    <div>
                      <Input
                        name="phoneNumber"
                        type="text"
                        label="Phone Number:"
                        labelPlacement="outside"
                        placeholder="Enter Phone Number"
                        color="default"
                        step={"1"}
                        onChange={inputForm}
                        className="w-72"
                      />

                      <Select
                        name="bootcamp"
                        label="Bootcamp :"
                        labelPlacement="outside"
                        placeholder="Please choose bootcamp..."
                        onChange={inputForm}
                      >
                        {bootcamps.map((bootcamp: string) => (
                          <SelectItem key={bootcamp} value={bootcamp}>
                            {bootcamp}
                          </SelectItem>
                        ))}
                      </Select>
                    </div>
                  ) : (
                    <div />
                  )}
                </div>
              )}
            </div>
          </div>
          <div className="flex flex-row justify-center">
            <Button
              className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
              placeholder={undefined}
              type="submit"
            >
              Submit
            </Button>
          </div>
        </form>
      </div>
    );
  }
};

export default Page;

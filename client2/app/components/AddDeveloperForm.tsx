import React, { ReactNode, useState } from "react";
import { Card, CardHeader, Input, Select, SelectItem } from "@nextui-org/react";
import { Button } from "@material-tailwind/react";
import { Developer, DeveloperFormInfo, PostDeveloperFunction } from "@/server";

export const AddDeveloperForm = ({
  postDeveloper,
  accountId,
  setDevelopers,
}: {
  postDeveloper: PostDeveloperFunction;
  accountId: string;
  setDevelopers: Function;
}): ReactNode => {
  const [developer, setDeveloper] = useState<DeveloperFormInfo>({
    firstName: "",
    lastName: "",
    emailAddress: "",
    bootcampCourse: "",
    githubUsername: "",
    accountId: accountId,
  });

  const handleInputChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = event.target;
    setDeveloper({
      ...developer,
      [name]: value,
    });
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    await postDeveloper({ developer });
    setDevelopers((developers: Developer[]) => [...developers, developer]);
  };

  return (
    <div className="h-full">
      <Card className="flex mx-10 my-5 px-10">
        <CardHeader>
          <h4 className="font-bold text-large">Add new Developer</h4>
        </CardHeader>
        <form
          id="form"
          onSubmit={handleSubmit}
          className="flex flex-col h-full justify-start gap-4"
        >
          <div className=" w-full p-4 flex flex-row gap-4">
            <Input
              type="text"
              id="firstName"
              name="firstName"
              label="First Name:"
              labelPlacement="outside-left"
              placeholder="Enter title"
              value={developer.firstName}
              onChange={handleInputChange}
              className="w-72"
            />
            <Input
              type="text"
              id="lastName"
              name="lastName"
              label="Last Name:"
              labelPlacement="outside-left"
              placeholder="Enter score"
              value={developer.lastName}
              onChange={handleInputChange}
              className="w-72"
            />
            <Input
              type="text"
              id="emailAddress"
              name="emailAddress"
              label="Email:"
              labelPlacement="outside-left"
              placeholder="Enter email"
              value={developer.emailAddress}
              onChange={handleInputChange}
              className="w-72"
            />
            <Input
              type="text"
              id="githubUsername"
              name="githubUsername"
              label="Github Username:"
              labelPlacement="outside-left"
              placeholder="Enter github username"
              value={developer.githubUsername}
              onChange={handleInputChange}
              className="w-80"
            />
          </div>
          <Select
            id="bootcampCourse"
            name="bootcampCourse"
            label="Bootcamp:"
            labelPlacement="outside-left"
            placeholder="Please choose a bootcamp..."
            className="w-96"
            onChange={handleInputChange}
          >
            <SelectItem key="Javascript" value={"Javascript"}>
              Javascript
            </SelectItem>
            <SelectItem key="Java" value={"Java"}>
              Java
            </SelectItem>
            <SelectItem key=".Net" value={".Net"}>
              .Net
            </SelectItem>
          </Select>
          <div className="flex flex-row justify-center">
            <Button
              className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded mb-5"
              placeholder={undefined}
              type="submit"
            >
              ADD
            </Button>
          </div>
        </form>
      </Card>
    </div>
  );
};

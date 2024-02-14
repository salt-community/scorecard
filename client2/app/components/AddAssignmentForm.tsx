import React, { ReactNode, useState } from "react";

import {
  Card,
  CardHeader,
  Input,
  Select,
  SelectItem,
  Textarea,
} from "@nextui-org/react";
import { Button } from "@material-tailwind/react";

type AssignmentFormInfo = {
  title: string;
  score: string;
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
    score: "",
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
    <div className="h-full">
      <Card>
        <CardHeader>
          <h4 className="font-bold text-large">Input Assignment</h4>
        </CardHeader>
        <form
          id="form"
          onSubmit={submitAssignment}
          className="flex flex-col h-full justify-start gap-4"
        >
          <div className=" w-full p-4 flex flex-col gap-4">
            <div className="flex flex-row gap-4 w-full">
              <Input
                type="text"
                id="title"
                name="title"
                label="Title :"
                labelPlacement="outside-left"
                placeholder="Enter title"
                value={assignment.title}
                onChange={handleInputChange}
                className="w-72"
              />
              <Input
                type="text"
                id="score"
                name="score"
                label="Score :"
                labelPlacement="outside-left"
                placeholder="Enter score"
                value={assignment.score}
                onChange={handleInputChange}
                className="w-72"
              />
            </div>
            <Textarea
              type="text"
              id="description"
              name="description"
              label="Description :"
              labelPlacement="outside"
              placeholder="Enter description"
              value={assignment.description}
              onChange={handleInputChange}
            />
          </div>
          <Select 
          id="category" 
          name="category"
          label="Category :"
          labelPlacement="outside"
          placeholder="Please choose a category..." 
          onChange={handleInputChange}>
            <SelectItem key="category-backend" value={"Backend"}>Backend</SelectItem>
            <SelectItem key="category-frontend" value={"Frontend"}>Frontend</SelectItem>
          </Select>
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
      </Card>
    </div>
  );
};

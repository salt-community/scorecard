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
import { PostAssignmentFunction } from "@/server";

type Assignment = {
  assignmentId: string;
  title: string;
  score: number;
  description: string;
  category: string;
  developerId: string;
};

export type AssignmentFormInfo = {
  developerId: string;
  title: string;
  score: number;
  description: string;
  category: string;
};

export const AddAssignmentForm = ({
  developerId,
  postAssignment,
  setAssignments
}: {
  developerId: string;
  postAssignment: PostAssignmentFunction;
  setAssignments: Function
}): ReactNode => {
  const [assignment, setAssignment] = useState<AssignmentFormInfo>({
    developerId: developerId,
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

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    await postAssignment({ assignment });
    setAssignments((assignments: Assignment[]) => [...assignments, assignment]);
  };

  console.log(assignment);

  return (
    <div className="h-full">
      <Card className="flex mx-10 my-5 px-10">
        <CardHeader>
          <h4 className="font-bold text-large">Input Assignment</h4>
        </CardHeader>
        <form
          id="form"
          onSubmit={handleSubmit}
          className="flex flex-col h-full justify-start gap-4"
        >
          <div className=" w-full p-4 flex flex-row gap-4">
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
              value={assignment.score.toString()}
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
            className="w-72"
            value={assignment.description}
            onChange={handleInputChange}
          />
          <Select
            id="category"
            name="category"
            label="Category :"
            labelPlacement="outside"
            placeholder="Please choose a category..."
            className="w-72"
            onChange={handleInputChange}
          >
            <SelectItem key="Backend" value={"Backend"}>
              Backend
            </SelectItem>
            <SelectItem key="Frontend" value={"Frontend"}>
              Frontend
            </SelectItem>
          </Select>
          <div className="flex flex-row justify-center">
            <Button
              className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded mb-5"
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

"use client";
import {
  CardHeader,
  Input,
  Select,
  SelectItem,
  Textarea,
} from "@nextui-org/react";
import React, { useState } from "react";
import { Button } from "@material-tailwind/react";
import { Assignment, SaltieData } from "@/app/types";
import { httpPostScoreById } from "@/app/api/request";

type InputFormProps = {
  developer: SaltieData;
  assignment: Assignment[];
  updateScore: Function;
};

const InputForm = ({ developer, assignment, updateScore }: InputFormProps) => {
  const [input, setInput] = useState({
    name: "",
    score: 0,
    description: "",
  });

  const inputForm = (el: any) => {
    const { name, value } = el.target;
    console.log(value);
    setInput({
      ...input,
      [name]: value,
    });
  };

  const submitHandler = async (e: any, input: any) => {
    e.preventDefault();

    const response = await httpPostScoreById(developer.id, input);
    if (response.status == 500) {
      alert("Score already exist");
    } else {
      e.target.reset();
      updateScore(response);
    }
  };
  return (
    <div className="h-full">
      <CardHeader>
        <h4 className="font-bold text-large">Input Score</h4>
      </CardHeader>

      <form
        id="form"
        onSubmit={(e) => submitHandler(e, input)}
        className="flex flex-col h-full justify-start gap-4"
      >
        <div className=" w-full p-4 flex flex-col gap-4">
          <div className="flex flex-row gap-4 w-full">
            <Select
              name="name"
              label="Assignment :"
              labelPlacement="outside"
              placeholder="Please choose assignment..."
              onChange={inputForm}
            >
              {assignment.map((assignment: Assignment) => (
                <SelectItem key={assignment.name} value={assignment.name}>
                  {assignment.name}
                </SelectItem>
              ))}
            </Select>
            <Input
              name="score"
              type="number"
              label="Score :"
              labelPlacement="outside"
              placeholder="Enter score"
              color="default"
              step={"1"}
              onChange={inputForm}
              className="w-72"
            />
          </div>
          <Textarea
            name="description"
            label="Description :"
            labelPlacement="outside"
            placeholder="Enter score description"
            onChange={inputForm}
          />
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
};

export default InputForm;

"use client";
import { TrashIcon } from "@heroicons/react/24/solid";
import { Button } from "@material-tailwind/react";
import { Input, Select, SelectItem } from "@nextui-org/react";
import React, { useState } from "react";

const SelectLanguages = () => {
  const [languages, setLanguages] = useState([{ language: "", fluency: "" }]);
  const fluency = ["beginer", "intermediate", "fluent", "natives"];

  const handleAddInput = () => {
    setLanguages([...languages, { language: "", fluency: "" }]);
  };

  const handleChange = (event: any, index: any) => {
    let { name, value } = event.target;
    let onChangeValue = [...languages];
    onChangeValue[index][name] = value;
    setLanguages(onChangeValue);
  };

  const handleDeleteInput = (index: any) => {
    const newArray = [...languages];
    newArray.splice(index, 1);
    setLanguages(newArray);
  };
  return (
    <div className="container flex flex-col gap-2">
      <div className="flex flex-row justify-between">
        <h4 className="font-bold text-large">Languages</h4>
        <Button
          className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
          placeholder={undefined}
          onClick={() => handleAddInput()}
        >
          Add
        </Button>
      </div>
      <div className="flex flex-row justify-around">
        <h5 className=" text-small">Languages</h5>
        <h5 className=" text-small">Fluency Level</h5>
      </div>
      {languages.map((item, index) => (
        <div className="flex flex-row justify-around gap-2" key={index}>
          <Input
            endContent={
              languages.length > 1 && (
                <TrashIcon
                  onClick={() => handleDeleteInput(index)}
                  className="w-8 h-8 text-default-400 flex-shrink-0 cursor-pointer hover:text-red-500"
                />
              )
            }
            name="language"
            type="text"
            value={item.language}
            className=""
            onChange={(event) => handleChange(event, index)}
          />
          <Select
            name="fluency"
            placeholder="Level"
            value={item.fluency}
            onChange={(event) => handleChange(event, index)}
          >
            {fluency.map((roles: string) => (
              <SelectItem key={roles} value={roles}>
                {roles}
              </SelectItem>
            ))}
          </Select>
          {/* 
          {languages.length > 1 && (
            <TrashIcon
              onClick={() => handleDeleteInput(index)}
              className="w-8 h-8 text-default-400 flex-shrink-0 cursor-pointer hover:text-red-500"
            />
          )} */}
        </div>
      ))}
    </div>
  );
};

export default SelectLanguages;

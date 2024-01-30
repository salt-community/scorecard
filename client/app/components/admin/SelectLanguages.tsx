"use client";
import { language } from "@/app/types";
import { TrashIcon } from "@heroicons/react/24/solid";
import { Button } from "@material-tailwind/react";
import { Input, Select, SelectItem } from "@nextui-org/react";
import React, { useEffect, useState } from "react";

type Props = {
  languagesSet: language[];
};

const SelectLanguages = ({ languagesSet }: Props) => {
  const [languages, setLanguages] = useState<language[]>([]);
  const fluency = ["beginer", "intermediate", "fluent", "natives"];

  const populateLanguages = () => {
    for (let i = 0; i < languagesSet.length; i++) {
      updateLanguages(languagesSet[i]);
    }
  };

  const updateLanguages = (language: language) => {
    setLanguages((curr: language[]) => [...curr, language]);
  };

  const handleAddInput = () => {
    setLanguages([...languages, { id: "", language: "", fluency: "" }]);
  };

  const handleChange = (event: any, index: any) => {
    let { name, value } = event.target;
    let onChangeValue:any = [...languages];
    onChangeValue[index][name] = value;
    setLanguages(onChangeValue);
  };

  const handleDeleteInput = (index: any) => {
    const newArray = [...languages];
    newArray.splice(index, 1);
    setLanguages(newArray);
  };

  useEffect(() => {
    populateLanguages();
  }, []);

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
            aria-label="enter language"
            name="language"
            type="text"
            value={item.language}
            className=""
            onChange={(event) => handleChange(event, index)}
          />
          <Select
            name="fluency"
            aria-label="enter fluency level"
            placeholder={item.fluency ? item.fluency : "level"}
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

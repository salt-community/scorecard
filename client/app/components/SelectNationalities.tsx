"use client";
import { CardHeader, Input, Select, SelectItem } from "@nextui-org/react";
import React, { useState } from "react";
import { TrashIcon } from "@heroicons/react/24/solid";
import { Button } from "@material-tailwind/react";

const SelectNationalities = () => {
  const [nationalities, setNationalities] = useState([{ skill: "" }]);

  const handleAddInput = () => {
    setNationalities([...nationalities, { skill: "" }]);
  };

  const handleChange = (event: any, index: any) => {
    let { name, value } = event.target;
    let onChangeValue = [...nationalities];
    onChangeValue[index][name] = value;
    setNationalities(onChangeValue);
    console.log(nationalities);
  };

  const handleDeleteInput = (index: any) => {
    const newArray = [...nationalities];
    newArray.splice(index, 1);
    setNationalities(newArray);
  };
  return (
    <div className="container flex flex-col gap-2">
      <div className="flex flex-row justify-between">
        <h4 className="font-bold text-large">Nationalities</h4>
        <Button
          className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
          placeholder={undefined}
          onClick={() => handleAddInput()}
        >
          Add
        </Button>
      </div>

      {nationalities.map((item, index) => (
        <div className="input_container" key={index}>
          <Input
            endContent={
              nationalities.length > 1 && (
                <TrashIcon
                  onClick={() => handleDeleteInput(index)}
                  className="w-8 h-8 text-default-400 flex-shrink-0 cursor-pointer hover:text-red-500"
                />
              )
            }
            name="skill"
            type="text"
            value={item.skill}
            className=""
            onChange={(event) => handleChange(event, index)}
          />
        </div>
      ))}
    </div>
  );
};

export default SelectNationalities;

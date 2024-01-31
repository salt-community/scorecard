"use client";
import { CardHeader, Input, Select, SelectItem } from "@nextui-org/react";
import React, { useEffect, useState } from "react";
import { TrashIcon } from "@heroicons/react/24/solid";
import { Button } from "@material-tailwind/react";
import { nationality } from "../../types";

type Props = {
  nationalitiesSet: nationality[];
};

const SelectNationalities = ({ nationalitiesSet }: Props) => {
  const [nationalities, setNationalities] = useState<nationality[]>([]);

  const populateNationalities = () => {
    for (let i = 0; i < nationalitiesSet?.length; i++) {
      updateNationality(nationalitiesSet[i]);
    }
  };

  const updateNationality = (nationality: nationality) => {
    setNationalities((curr: nationality[]) => [...curr, nationality]);
  };

  const handleAddInput = () => {
    setNationalities([...nationalities, { id: "", nationality: "" }]);
  };

  const handleChange = (event: any, index: any) => {
    let { name, value } = event.target;
    let onChangeValue: any = [...nationalities];
    onChangeValue[index][name] = value;
    setNationalities(onChangeValue);
    console.log(nationalities);
  };

  const handleDeleteInput = (index: any) => {
    const newArray = [...nationalities];
    newArray.splice(index, 1);
    setNationalities(newArray);
  };

  useEffect(() => {
    populateNationalities();
  }, []);
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
      <div className="grid grid-cols-2 gap-2">
        {nationalities.map((item, index) => (
          <div className="w-full " key={index}>
            <Input
              endContent={
                nationalities.length > 1 && (
                  <TrashIcon
                    onClick={() => handleDeleteInput(index)}
                    className="w-8 h-8 text-default-400 flex-shrink-0 cursor-pointer hover:text-red-500"
                  />
                )
              }
              aria-label="enter nationality"
              name="nationality"
              type="text"
              labelPlacement="outside-left"
              value={item.nationality}
              className="w-full"
              onChange={(event) => handleChange(event, index)}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default SelectNationalities;

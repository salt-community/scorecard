"use client";
import { Input } from "@nextui-org/react";
import React, { useEffect, useState } from "react";
import { TrashIcon } from "@heroicons/react/24/solid";
import { Button } from "@material-tailwind/react";
import { skill } from "@/app/types";

type Props = {
  skillsSet: skill[];
  onSkillsChange: Function;
};

const SelectSkills = ({ skillsSet, onSkillsChange }: Props) => {
  const [skills, setskills] = useState<skill[]>([]);

  const populateSkill = () => {
    for (let i = 0; i < skillsSet.length; i++) {
      updateSkill(skillsSet[i]);
    }
  };

  const updateSkill = (skill: skill) => {
    setskills((curr: skill[]) => [...curr, skill]);
  };

  const handleAddInput = () => {
    setskills([...skills, { id: "", skill: "" }]);
  };

  const handleChange = (event: any, index: number) => {
    let { name, value } = event.target;
    let onChangeValue: any = [...skills];
    onChangeValue[index][name] = value;
    setskills(onChangeValue);
    onSkillsChange(skills);
  };

  const handleDeleteInput = (index: number) => {
    const newArray = [...skills];
    newArray.splice(index, 1);
    setskills(newArray);
    onSkillsChange(newArray);
  };

  useEffect(() => {
    populateSkill();
  }, []);

  return (
    <div className="container flex flex-col gap-2">
      <div className="flex flex-row justify-between">
        <h4 className="font-bold text-large">Skills</h4>
        <Button
          className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
          placeholder={undefined}
          onClick={() => handleAddInput()}
        >
          Add
        </Button>
      </div>
      <div className="grid grid-cols-2 gap-2">
        {skills.map((item, index) => (
          <div className="" key={index}>
            <Input
              endContent={
                skills.length > 1 && (
                  <TrashIcon
                    onClick={() => handleDeleteInput(index)}
                    className="w-8 h-8 text-default-400 flex-shrink-0 cursor-pointer hover:text-red-500"
                  />
                )
              }
              labelPlacement="outside-left"
              aria-label="enter skill"
              name="skill"
              type="text"
              value={item.skill}
              className="flex-1"
              onChange={(event) => handleChange(event, index)}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default SelectSkills;

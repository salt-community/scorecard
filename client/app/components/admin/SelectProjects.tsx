"use client";
import { Input } from "@nextui-org/react";
import React, { useEffect, useState } from "react";
import { TrashIcon } from "@heroicons/react/24/solid";
import { Button } from "@material-tailwind/react";
import { project } from "@/app/types";

type Props = {
  projectsSet: project[];
  onProjectsChange: Function;
};

const SelectProjects = ({ projectsSet, onProjectsChange }: Props) => {
  const [projects, setProjects] = useState<project[]>([]);

  const populateProject = () => {
    for (let i = 0; i < projectsSet.length; i++) {
      updateProject(projectsSet[i]);
    }
  };

  const updateProject = (project: project) => {
    setProjects((curr: project[]) => [...curr, project]);
  };

  const handleAddInput = () => {
    setProjects([
      ...projects,
      {
        id: "",
        url: "",
        commit: "",
        issue: "",
        duration: "",
        performance: "",
        testCoverage: "",
      },
    ]);
  };

  const handleChange = (event: any, index: number) => {
    let { name, value } = event.target;
    let onChangeValue: any = [...projects];
    onChangeValue[index][name] = value;
    setProjects(onChangeValue);
    onProjectsChange(projects);
  };

  const handleDeleteInput = (index: number) => {
    const newArray = [...projects];
    newArray.splice(index, 1);
    setProjects(newArray);
    onProjectsChange(newArray);
  };

  useEffect(() => {
    populateProject();
  }, []);

  return (
    <div className="container flex flex-col gap-2">
      <div className="flex flex-row justify-between">
        <h4 className="font-bold text-large">Projects Link</h4>
        <Button
          className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
          placeholder={undefined}
          onClick={() => handleAddInput()}
        >
          Add
        </Button>
      </div>

      {projects.map((item, index) => (
        <div className="" key={index}>
          <Input
            endContent={
              projects.length > 1 && (
                <TrashIcon
                  onClick={() => handleDeleteInput(index)}
                  className="w-8 h-8 text-default-400 flex-shrink-0 cursor-pointer hover:text-red-500"
                />
              )
            }
            aria-label="enter project"
            name="url"
            type="text"
            value={item.url}
            className=""
            onChange={(event) => handleChange(event, index)}
          />
        </div>
      ))}
    </div>
  );
};

export default SelectProjects;

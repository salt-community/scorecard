"use client";
import { Card } from "@nextui-org/react";
import React from "react";
import ScoreList from "./ScoreList";
import { Assignment, DeveloperData, SaltieData, Scores } from "@/app/types";
import InputForm from "./InputForm";

interface ScoreboardProps {
  developer: SaltieData;
  assignment: Assignment[];
}

const ScoreboardBody = ({ developer, assignment }: ScoreboardProps) => {
  return (
    <div className="h-full flex flex-row gap-4">
      <Card className="w-72 block min-h-full p-2">
        <ScoreList scores={developer.scores} />
      </Card>
      <Card className="flex-1 min-h-full">
        <InputForm developer={developer} assignment={assignment} />
      </Card>
    </div>
  );
};

export default ScoreboardBody;

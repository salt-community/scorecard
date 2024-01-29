"use client";
import { Card } from "@nextui-org/react";
import React, { useEffect, useState } from "react";
import ScoreList from "./ScoreList";
import { Assignment, DeveloperData, SaltieData, Score, ScoreRes } from "@/app/types";
import InputForm from "./InputForm";

interface ScoreboardProps {
  developer: SaltieData;
  assignment: Assignment[];
}

const ScoreboardBody = ({ developer, assignment }: ScoreboardProps) => {
  const [score, setScore] = useState<ScoreRes[]>(developer.scores);
  

  const updateScore = (updatedScore: ScoreRes) => {
    setScore((curr: ScoreRes[]) => [...curr, updatedScore]);
    console.log(score);
  };
  return (
    <div className="h-full flex flex-row gap-4">
      <Card className="w-72 block min-h-full p-2">
        <ScoreList scores={score!}/>
      </Card>
      <Card className="flex-1 min-h-full">
        <InputForm developer={developer} assignment={assignment} updateScore={updateScore}  />
      </Card>
    </div>
  );
};

export default ScoreboardBody;

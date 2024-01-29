"use client";
import { Card } from "@nextui-org/react";
import React, { useEffect, useState } from "react";
import ScoreList from "./ScoreList";
import {
  Assignment,
  DeveloperData,
  SaltieData,
  Score,
  ScoreRes,
} from "@/app/types";
import InputForm from "./InputForm";
import UpdateForm from "./UpdateForm";

interface ScoreboardProps {
  developer: SaltieData;
  assignment: Assignment[];
}

const ScoreboardBody = ({ developer, assignment }: ScoreboardProps) => {
  const [score, setScore] = useState<ScoreRes[]>(developer.scores);
  const [searchedScore, setSearchedScore] = useState<ScoreRes>();

  const updateScore = (updatedScore: ScoreRes) => {
    setScore((curr: ScoreRes[]) => [...curr, updatedScore]);
  };

  const deleteSearchedScore = () => {
    setSearchedScore(undefined);
  };

  const searchScore = (assignment: string) => {
    const searchedScore = score.filter(
      (score) => score.assignment === assignment
    );
    setSearchedScore(searchedScore[0]);
  };

  return (
    <div className="h-full flex flex-row gap-4">
      <Card className="w-72 block min-h-full p-2">
        <ScoreList scores={score!} searchScore={searchScore} />
      </Card>
      <Card className="flex-1 min-h-full">
        {searchedScore ? (
          <UpdateForm
            developer={developer}
            assignment={assignment}
            updateScore={updateScore}
            deleteSearchedScore={deleteSearchedScore}
            searchedScore={searchedScore}
          />
        ) : (
          <InputForm
            developer={developer}
            assignment={assignment}
            updateScore={updateScore}
          />
        )}
      </Card>
    </div>
  );
};

export default ScoreboardBody;

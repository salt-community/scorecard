"use client";
import { Card } from "@nextui-org/react";
import React, { useState } from "react";
import ScoreList from "./ScoreList";
import { Assignment, SaltieData, Score } from "@/app/types";
import InputForm from "./InputForm";
import UpdateForm from "./UpdateForm";

type ScoreboardProps = {
  developer: SaltieData;
  assignment: Assignment[];
};

const ScoreboardBody = ({ developer, assignment }: ScoreboardProps) => {
  const [score, setScore] = useState<Score[]>(developer.scores);
  const [searchedScore, setSearchedScore] = useState<Score>();

  const updateScore = (updatedScore: Score) => {
    setScore((curr: Score[]) => [...curr, updatedScore]);
  };

  const deleteScore = (id: string) => {
    setScore(score.filter((score) => score.id !== id));
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
        <ScoreList
          scores={score!}
          searchScore={searchScore}
          averages={developer.averages}
        />
      </Card>
      <Card className="flex-1 min-h-full">
        {searchedScore ? (
          <UpdateForm
            assignment={assignment}
            deleteSearchedScore={deleteSearchedScore}
            deleteScore={deleteScore}
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

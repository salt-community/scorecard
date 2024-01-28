"use client";
import { Assignment, DeveloperData } from "@/app/types";
import ScoreboardHeader from "./ScoreboardHeader";
import ScoreboardBody from "./ScoreboardBody";

interface ScoreboardProps {
  developer: DeveloperData;
  assignment: Assignment[];
}

const Scoreboard = ({ developer, assignment }: ScoreboardProps) => {
  return (
    <div className="flex flex-col gap-4 h-full">
      <ScoreboardHeader developer={developer} />
      <ScoreboardBody developer={developer} assignment={assignment} />
    </div>
  );
};

export default Scoreboard;

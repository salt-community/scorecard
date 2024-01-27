import { Card } from "@nextui-org/react";
import React from "react";
import ScoreList from "./ScoreList";
import { Scores } from "@/app/types";

interface ScoreboardProps {
  scores: Scores[];
}

const ScoreboardBody = ({ scores }: ScoreboardProps) => {
  return (
    <div className="h-full flex flex-row gap-4">
      <Card className="w-72 block min-h-full">
        <ScoreList scores={scores} />
      </Card>
      <Card className="flex-1 min-h-full">
        <ScoreList scores={scores} />
      </Card>
    </div>
  );
};

export default ScoreboardBody;

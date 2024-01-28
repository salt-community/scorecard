"use client";

import { DeveloperData, RadarGraphicData } from "@/app/types";
import { isExcellent } from "@/app/utilities";
import { Card, CardHeader } from "@nextui-org/react";
import Grading from "./Grading";
import ScoreboardId from "./ScoreboardId";

interface ScoreboardProps {
  developer: DeveloperData;
}

const ScoreboardHeader = ({ developer }: ScoreboardProps) => {
  return (
    <div className="flex flex-row gap-4">
      <Card
        className={`w-72 ${
          isExcellent(developer) ? "border-8 border-purple-600" : ""
        }`}
      >
        <CardHeader className=" px-4">
          <ScoreboardId developer={developer} />
        </CardHeader>
      </Card>
      <Card className=" w-96">
        <Grading />
      </Card>
      <Card className=" flex-1"></Card>
    </div>
  );
};

export default ScoreboardHeader;

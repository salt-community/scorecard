"use client";

import { DeveloperData, RadarGraphicData, SaltieData } from "@/app/types";
import {
  colorVariant,
  getAllAverageValue,
  isExcellent,
  levelVariant,
} from "@/app/utilities";
import { Card, CardHeader, Chip, CircularProgress } from "@nextui-org/react";
import Grading from "./Grading";
import ScoreboardId from "./ScoreboardId";

interface ScoreboardProps {
  developer: SaltieData;
}

const ScoreboardHeader = ({ developer }: ScoreboardProps) => {
  return (
    <div className="flex flex-row gap-4">
      <Card className={`w-72         `}>
        <CardHeader className=" px-4">
          <ScoreboardId developer={developer} />
        </CardHeader>
      </Card>
      <Card className=" w-96">
        <Grading />
      </Card>
      <Card className=" flex-1 flex-col justify-center items-center">
        {/* <Chip
          color={colorVariant(getAllAverageValue(developer.scores))}
          variant="bordered"
          classNames={{
            content: "drop-shadow shadow-black text-black",
            base: "py-6",
          }}
          startContent={
            <CircularProgress
              size="lg"
              value={getAllAverageValue(developer.scores)}
              color={colorVariant(getAllAverageValue(developer.scores))}
              showValueLabel={true}
              aria-label="score value"
            />
          }
        >
          <h4 className=" text-2xl mx-2">
            Level {levelVariant(getAllAverageValue(developer.scores))}
          </h4>
        </Chip> */}
      </Card>
    </div>
  );
};

export default ScoreboardHeader;

"use client";
import { SaltieData } from "@/app/types";
import { colorVariant, levelVariant } from "@/app/utilities";
import { Card, CardHeader, Chip, CircularProgress } from "@nextui-org/react";
import Grading from "./Grading";
import ScoreboardId from "./ScoreboardId";

type ScoreboardHeaderProps = {
  developer: SaltieData;
};

const ScoreboardHeader = ({ developer }: ScoreboardHeaderProps) => {
  const totalScoreAverage = developer.averages.filter(
    (average) => average.scoreName === "total"
  )[0].average;

  return (
    <div className="flex flex-row gap-4">
      <Card className={`w-72`}>
        <CardHeader className=" px-4">
          <ScoreboardId developer={developer} />
        </CardHeader>
      </Card>
      <Card className=" w-96">
        <Grading />
      </Card>
      <Card className=" flex-1 flex-col justify-center items-center">
        <Chip
          color={colorVariant(totalScoreAverage)}
          variant="bordered"
          classNames={{
            content: "drop-shadow shadow-black text-black",
            base: "py-6",
          }}
          startContent={
            <CircularProgress
              size="lg"
              value={totalScoreAverage}
              color={colorVariant(totalScoreAverage)}
              showValueLabel={true}
              aria-label="score value"
            />
          }
        >
          <h4 className="text-large mx-2">
            Level {levelVariant(totalScoreAverage)}
          </h4>
        </Chip>
      </Card>
    </div>
  );
};

export default ScoreboardHeader;

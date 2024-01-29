"use client";

import { SaltieData, ScoreRes, Scores } from "@/app/types";
import {
  colorVariant,
  getAllAverageValue,
  levelVariant,
} from "@/app/utilities";
import { Card, CardHeader, Chip, CircularProgress } from "@nextui-org/react";
import Grading from "./Grading";
import ScoreboardId from "./ScoreboardId";

interface ScoreboardProps {
  developer: SaltieData;
}

const ScoreboardHeader = ({ developer }: ScoreboardProps) => {
  const scoreData = (scores: ScoreRes[]) => {
    const type: string[] = scores?.map((score) => score.type);
    function onlyUnique(value: any, index: any, array: any) {
      return array.indexOf(value) === index;
    }

    var unique = type?.filter(onlyUnique);
    const data: Scores[] = [];
    for (let i = 0; i < unique?.length; i++) {
      const scoreName = unique[i];
      const data1 = scores
        .filter((score) => score.type === scoreName)
        .reduce(function (r: any, e) {
          r[e.assignment] = e.score;
          return r;
        }, {});

      const x: Scores = {
        scoreName: scoreName,
        data: data1,
      };
      data.push(x);
    }

    return data;
  };
  const formattedScores = scoreData(developer.scores);

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
        <Chip
          color={colorVariant(getAllAverageValue(formattedScores))}
          variant="bordered"
          classNames={{
            content: "drop-shadow shadow-black text-black",
            base: "py-6",
          }}
          startContent={
            <CircularProgress
              size="lg"
              value={getAllAverageValue(formattedScores)}
              color={colorVariant(getAllAverageValue(formattedScores))}
              showValueLabel={true}
              aria-label="score value"
            />
          }
        >
          <h4 className=" text-2xl mx-2">
            Level {levelVariant(getAllAverageValue(formattedScores))}
          </h4>
        </Chip>
      </Card>
    </div>
  );
};

export default ScoreboardHeader;

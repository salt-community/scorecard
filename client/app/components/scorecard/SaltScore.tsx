import {
  Card,
  CardHeader,
  CardBody,
  Accordion,
  AccordionItem,
  CircularProgress,
  Chip,
} from "@nextui-org/react";
import SimpleTable from "./SimpleTable";
import {
  Average,
  RadarGraphicData,
  Score,
  ScoreRes,
  Scores,
} from "../../types";
import { RadarGraphic } from "./RadarGraphic";
/* import {
  colorVariant,
  getAllAverageValue,
  levelVariant,
  getAverageValue,
} from "../../utilities"; */
import ScoreEntry from "./ScoreEntry";
import {
  capitalizeEveryWord,
  colorVariant,
  levelVariant,
} from "@/app/utilities";

interface SaltScoreProps {
  scores: ScoreRes[];
  radarGraphicData: RadarGraphicData[];
  averages: Average[];
}

const SaltScore = ({ scores, radarGraphicData, averages }: SaltScoreProps) => {
  const scoreData = (scores: ScoreRes[]) => {
    const type = ["communication", "coding", "planning"];

    function onlyUnique(value: any, index: any, array: any) {
      return array.indexOf(value) === index;
    }

    var unique = type?.filter(onlyUnique);
    const data: any[] = [];
    for (let i = 0; i < unique?.length; i++) {
      const scoreName = unique[i];
      const data1 = scores.filter((score) => score.type === scoreName);
      const filteredAverage = averages.filter(
        (avg) => avg.scoreName === scoreName
      )[0];
      const x: any = {
        scoreName: scoreName,
        average: filteredAverage.average,
        data: data1,
      };
      data.push(x);
    }

    return data;
  };
  const totalAverageNumber = averages.filter((a) => a.scoreName === "total")[0]
    .average;
  const data = scoreData(scores);
  return (
    <>
      <h4 className="font-bold text-large">Salt Scoring</h4>
      <Card shadow="sm">
        <CardHeader className="flex flex-row gap-2">
          <Chip
            color={colorVariant(totalAverageNumber)}
            variant="bordered"
            classNames={{
              content: "drop-shadow shadow-black text-black",
              base: "py-6",
            }}
            startContent={
              <CircularProgress
                size="md"
                value={totalAverageNumber}
                color={colorVariant(totalAverageNumber)}
                showValueLabel={true}
                aria-label="score value"
              />
            }
          >
            <h4 className="text-large mx-2">
              Level {levelVariant(totalAverageNumber)}
            </h4>
          </Chip>
        </CardHeader>
        <CardBody className="text-small">
          <RadarGraphic data={radarGraphicData} />
          <Accordion>
            {data.map((item) => (
              <AccordionItem
                key={item.scoreName}
                title={capitalizeEveryWord(item.scoreName)}
                startContent={
                  <CircularProgress
                    size="md"
                    value={item.average}
                    color={colorVariant(item.average)}
                    showValueLabel={true}
                    aria-label="score value"
                  />
                }
              >
                {item.data.map((d, index) => (
                  <ScoreEntry data={d} key={index} />
                ))}
              </AccordionItem>
            ))}
          </Accordion>
        </CardBody>
      </Card>
    </>
  );
};

export default SaltScore;

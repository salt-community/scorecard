import {
  Card,
  CardHeader,
  CardBody,
  Accordion,
  AccordionItem,
  CircularProgress,
  Chip,
} from "@nextui-org/react";
import { Average, DetailScores, RadarGraphicData, Score } from "../../types";
import { RadarGraphic } from "./RadarGraphic";
import ScoreEntry from "./ScoreEntry";
import {
  capitalizeEveryWord,
  colorVariant,
  levelVariant,
} from "@/app/utilities";

type SaltScoreProps = {
  scores: Score[];
  radarGraphicData: RadarGraphicData[];
  averages: Average[];
};

const SaltScore = ({ scores, radarGraphicData, averages }: SaltScoreProps) => {
  const scoreData = (scores: Score[]) => {
    const type: string[] = scores?.map((score) => score.type);
    function onlyUnique(value: any, index: any, array: any) {
      return array.indexOf(value) === index;
    }

    var unique = type?.filter(onlyUnique);
    const detailScores: DetailScores[] = [];
    for (let i = 0; i < unique.length; i++) {
      const scoreName = unique[i];
      const scoreData = scores.filter((score) => score.type === scoreName);
      const averageScore = averages.filter(
        (avg) => avg.scoreName === scoreName
      )[0].average;
      const detailScore: DetailScores = {
        scoreName: scoreName,
        average: averageScore,
        data: scoreData,
      };
      detailScores.push(detailScore);
    }
    return detailScores;
  };

  const totalScoreAverage = averages.filter((a) => a.scoreName === "total")[0]
    .average;
  const detailScores = scoreData(scores);
  return (
    <>
      <h4 className="font-bold text-large">Salt Scoring</h4>
      <Card shadow="sm">
        <CardHeader className="flex flex-row gap-2">
          <Chip
            color={colorVariant(totalScoreAverage)}
            variant="bordered"
            classNames={{
              content: "drop-shadow shadow-black text-black",
              base: "py-6",
            }}
            startContent={
              <CircularProgress
                size="md"
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
        </CardHeader>
        <CardBody className="text-small">
          <RadarGraphic data={radarGraphicData} />
          <Accordion>
            {detailScores.map(({ scoreName, average, data }) => (
              <AccordionItem
                key={scoreName}
                title={capitalizeEveryWord(scoreName)}
                startContent={
                  <CircularProgress
                    size="md"
                    value={average}
                    color={colorVariant(average)}
                    showValueLabel={true}
                    aria-label="score value"
                  />
                }
              >
                {data.map((data, index) => (
                  <ScoreEntry data={data} key={index} />
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

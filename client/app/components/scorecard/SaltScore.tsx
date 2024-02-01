import {
  Card,
  CardHeader,
  CardBody,
  Accordion,
  AccordionItem,
  CircularProgress,
  Chip,
} from "@nextui-org/react";
import { Average, RadarGraphicData, Score } from "../../types";
import { RadarGraphic } from "./RadarGraphic";
import ScoreEntry from "./ScoreEntry";
import {
  capitalizeEveryWord,
  colorVariant,
  levelVariant,
  scoreData,
} from "@/app/utilities";

type SaltScoreProps = {
  scores: Score[];
  radarGraphicData: RadarGraphicData[];
  averages: Average[];
};

const SaltScore = ({ scores, radarGraphicData, averages }: SaltScoreProps) => {
  const totalScoreAverage = averages.filter((a) => a.scoreName === "total")[0]
    .average;
  const detailScores = scoreData(scores, averages);
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

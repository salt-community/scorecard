import {
  Card,
  CardHeader,
  CardBody,
  Accordion,
  AccordionItem,
  CircularProgress,
  Chip,
} from "@nextui-org/react";
/* import { Average, RadarGraphicData, Score } from "../../types"; */
/* import { RadarGraphic } from "./RadarGraphic"; */
/* import ScoreEntry from "./ScoreEntry";
import {
  capitalizeEveryWord,
  colorVariant,
  levelVariant,
  scoreData,
} from "@/app/utilities"; */

type SaltScoreProps = {
  /*   scores: Score[];
  radarGraphicData: RadarGraphicData[]; */
  averages: Average[];
};

export type Average = {
  averageBackendScore: number;
  averageFrontendScore: number;
};

export function capitalizeEveryWord(inputString: string): string {
  return inputString.replace(/\b\w/g, (match) => match.toUpperCase());
}

export const colorVariant = (value: number) => {
  if (value >= 90) {
    return "secondary";
  } else if (value > 70) {
    return "primary";
  } else {
    return "warning";
  }
};

export const levelVariant = (value: number) => {
  if (value >= 90) {
    return 3;
  } else if (value > 70) {
    return 2;
  } else {
    return 1;
  }
};

const SaltScore = ({ averageBackendScore, averageFrontendScore }: Average) => {
  const totalScoreAverage = Math.round((averageBackendScore+averageFrontendScore)/2)
  //const detailScores = scoreData(averages);
  var allScores: number[] = [];
  allScores.push(averageBackendScore, averageFrontendScore);
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
          {/* <RadarGraphic data={radarGraphicData} /> */}
          <Accordion>
                <AccordionItem
                  key={averageBackendScore}
                  title={capitalizeEveryWord('Backend')}
                  startContent={
                    <CircularProgress
                      size="md"
                      value={averageBackendScore}
                      color={colorVariant(averageBackendScore)}
                      showValueLabel={true}
                      aria-label="score value"
                    />
                  }
                />
                   <AccordionItem
                  key={averageFrontendScore}
                  title={capitalizeEveryWord('Frontend')}
                  startContent={
                    <CircularProgress
                      size="md"
                      value={averageFrontendScore}
                      color={colorVariant(averageFrontendScore)}
                      showValueLabel={true}
                      aria-label="score value"
                    />
                  }
                  />
                {/* </AccordionItem>
                 {/*  {data.map((data, index) => (
                    <ScoreEntry data={data} key={index} />
                  ))} */}
                {/* </AccordionItem>  */}
          </Accordion>
        </CardBody>
      </Card>
    </>
  );
};

export default SaltScore;

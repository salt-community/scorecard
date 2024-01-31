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
import { RadarGraphicData, Score, ScoreRes, Scores } from "../../types";
import { RadarGraphic } from "./RadarGraphic";
/* import {
  colorVariant,
  getAllAverageValue,
  levelVariant,
  getAverageValue,
} from "../../utilities"; */
import ScoreEntry from "./ScoreEntry";

interface SaltScoreProps {
  scores: ScoreRes[];
  radarGraphicData: RadarGraphicData[];
}

const SaltScore = ({ scores, radarGraphicData }: SaltScoreProps) => {
  const scoreData = (scores: ScoreRes[]) => {
    const type = ["communication", "coding", "planning"];

    function onlyUnique(value: any, index: any, array: any) {
      return array.indexOf(value) === index;
    }

    var unique = type?.filter(onlyUnique);
    const data: Scores[] = [];
    for (let i = 0; i < unique?.length; i++) {
      const scoreName = unique[i];
      const data1 = scores.filter((score) => score.type === scoreName);
      const x: Scores = {
        scoreName: scoreName,
        data: data1,
      };
      data.push(x);
    }

    return data;
  };
  const data = scoreData(scores);
  console.log(data);
  return (
    <>
      <h4 className="font-bold text-large">Salt Scoring</h4>
      <Card shadow="sm">
        <CardHeader className="flex flex-row gap-2">
          <Chip
            // color={colorVariant(getAllAverageValue(data))}
            variant="bordered"
            classNames={{
              content: "drop-shadow shadow-black text-black",
              base: "py-6",
            }}
            startContent={
              <CircularProgress
                size="md"
                // value={getAllAverageValue(data)}
                //color={colorVariant(getAllAverageValue(data))}
                showValueLabel={true}
                aria-label="score value"
              />
            }
          >
            {/*             <h4 className="text-large mx-2">
              Level {levelVariant(getAllAverageValue(data))}
            </h4> */}
          </Chip>
        </CardHeader>
        <CardBody className="text-small">
          <RadarGraphic data={radarGraphicData} />
          <Accordion>
            {data.map((item) => (
              <AccordionItem
                key={item.scoreName}
                title={item.scoreName}
                startContent={
                  <CircularProgress
                    size="md"
                    //value={getAverageValue(item.data)}
                    //color={colorVariant(getAverageValue(item.data))}
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

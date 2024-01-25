import {
  Card,
  CardHeader,
  CardBody,
  Accordion,
  AccordionItem,
  CircularProgress,
  Chip,
} from '@nextui-org/react';
import SimpleTable from './SimpleTable';
import { RadarGraphicData, Scores } from '../types';
import { RadarGraphic } from './RadarGraphic';
import {
  colorVariant,
  getAllAverageValue,
  levelVariant,
  getAverageValue,
} from '../utilities';

interface SaltScoreProps {
  scores: Scores[];
  radarGraphicData: RadarGraphicData[];
}

const SaltScore = ({ scores, radarGraphicData }: SaltScoreProps) => {
  return (
    <>
      <h4 className="font-bold text-large">Salt Scoring</h4>
      <Card shadow="sm">
        <CardHeader className="flex flex-row gap-2">
          <Chip
            color={colorVariant(getAllAverageValue(scores))}
            variant="bordered"
            classNames={{
              content: 'drop-shadow shadow-black text-black',
              base: 'py-6',
            }}
            startContent={
              <CircularProgress
                size="md"
                value={getAllAverageValue(scores)}
                color={colorVariant(getAllAverageValue(scores))}
                showValueLabel={true}
                aria-label="score value"
              />
            }
          >
            <h4 className="text-large mx-2">
              Level {levelVariant(getAllAverageValue(scores))}
            </h4>
          </Chip>
        </CardHeader>
        <CardBody className="text-small">
          <RadarGraphic data={radarGraphicData} />
          <Accordion>
            {scores.map(item => (
              <AccordionItem
                key={item.scoreName}
                title={item.scoreName}
                startContent={
                  <CircularProgress
                    size="md"
                    value={getAverageValue(item.data)}
                    color={colorVariant(getAverageValue(item.data))}
                    showValueLabel={true}
                    aria-label="score value"
                  />
                }
              >
                <SimpleTable data={item.data} />
              </AccordionItem>
            ))}
          </Accordion>
        </CardBody>
      </Card>
    </>
  );
};

export default SaltScore;

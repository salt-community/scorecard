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
import { Scores, SimpleTableEntry } from '../types';
import { RadarGraphic } from './RadarGraphic';

const getAverageValue = (data: SimpleTableEntry) => {
  let sum = 0;
  let count = 0;
  for (const key in data) {
    if (typeof data[key] === 'number') {
      sum += data[key] as number;
      count++;
    }
  }
  return sum / count;
};

const colorVariant = (value: number) => {
  if (value >= 90) {
    return 'secondary';
  } else if (value > 70) {
    return 'primary';
  } else {
    return 'warning';
  }
};
const levelVariant = (value: number) => {
  if (value >= 90) {
    return 3;
  } else if (value > 70) {
    return 2;
  } else {
    return 1;
  }
};

const getAllAverageValue = (
  scoreData: { scoreName: string; data: SimpleTableEntry }[]
) => {
  const allAveNum: number[] = [];
  scoreData.map(item => allAveNum.push(getAverageValue(item.data)));
  const sum = allAveNum.reduce((acc, curr) => acc + curr, 0);
  return sum / allAveNum.length;
};

interface SaltScoreProps {
  scores: Scores[];
}

const SaltScore = ({ scores }: SaltScoreProps) => {
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
          <RadarGraphic />
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

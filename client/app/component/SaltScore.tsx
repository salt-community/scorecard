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
import { SimpleTableEntry } from '../types';
import { RadarGraphic } from './RadarGraphic';

const scoreData: { scoreName: string; data: SimpleTableEntry }[] = [
  {
    scoreName: 'Communication',
    data: {
      'On site demo 1': 88,
      'On site demo 2': 86,
      'On site demo 3': 91,
      'Personal interview 1': 80,
      'Personal interview 2': 94,
      'Tech interview 1': 89,
      'Tech interview 2': 90,
      'Repository documentation': 89,
      'Tech article': 96,
      'Video demo 1': 90,
      'Video demo 2': 93,
      'Video demo 3': 89,
    },
  },
  {
    scoreName: 'Planning',
    data: {
      Microsteps: 82,
      'Test cases': 85,
      'Project board': 83,
      Figma: 89,
    },
  },
  {
    scoreName: 'Coding',
    data: {
      'Weekend Assignment 1': 100,
      'Weekend Assignment 2': 100,
      'Weekend Assignment 3': 100,
      'On site test 1': 97,
      'On site test 2': 90,
      'On site test 3': 91,
      'Hackday 1': 95,
      'Hackday 2': 96,
      'Hackday 3': 99,
      'Project 1': 97,
      'Project 2': 99,
    },
  },
];

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

const SaltScore = () => {
  return (
    <>
      <h4 className="font-bold text-large">Salt Scoring</h4>
      <Card shadow="sm">
        <CardHeader className="flex flex-row gap-2">
          <Chip
            color={colorVariant(getAllAverageValue(scoreData))}
            variant="bordered"
            classNames={{
              content: 'drop-shadow shadow-black text-black',
              base: 'py-6',
            }}
            startContent={
              <CircularProgress
                size="md"
                value={getAllAverageValue(scoreData)}
                color={colorVariant(getAllAverageValue(scoreData))}
                showValueLabel={true}
                aria-label="score value"
              />
            }
          >
            <h4 className="text-large mx-2">
              Level {levelVariant(getAllAverageValue(scoreData))}
            </h4>
          </Chip>
        </CardHeader>
        <CardBody className="text-small">
          <RadarGraphic />
          <Accordion>
            {scoreData.map(item => (
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

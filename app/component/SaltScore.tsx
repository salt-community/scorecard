import {
  Card,
  CardHeader,
  CardBody,
  Accordion,
  AccordionItem,
  CircularProgress,
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
      'On site demo 3': 81,
      'Personal interview 1': 70,
      'Personal interview 2': 94,
      'Tech interview 1': 89,
      'Tech interview 2': 80,
      'Repository documentation': 79,
      'Tech article': 96,
      'Video demo 1': 70,
      'Video demo 2': 93,
      'Video demo 3': 89,
    },
  },
  {
    scoreName: 'Planning',
    data: {
      Microsteps: 62,
      'Test cases': 65,
      'Project board': 63,
      Figma: 69,
    },
  },
  {
    scoreName: 'Coding',
    data: {
      'Weekend Assignment 1': 90,
      'Weekend Assignment 2': 94,
      'Weekend Assignment 3': 100,
      'On site test 1': 87,
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
  if (value > 90) {
    return 'success';
  } else if (value > 70) {
    return 'warning';
  } else {
    return 'danger';
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
    <Card shadow="sm" className="my-2">
      <CardHeader className="flex flex-row gap-2">
        <CircularProgress
          size="lg"
          value={getAllAverageValue(scoreData)}
          color={colorVariant(getAllAverageValue(scoreData))}
          showValueLabel={true}
          aria-label="score value"
        />
        <h4 className="text-large">He is great!</h4>
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
  );
};

export default SaltScore;

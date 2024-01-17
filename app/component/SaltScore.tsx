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

const scoreData: { scoreName: string; data: SimpleTableEntry }[] = [
  { scoreName: 'weekend test', data: { Week1: 90, Week2: 95 } },
  { scoreName: 'on site tests', data: { One: 50, Two: 95 } },
  { scoreName: 'test interviews', data: { One: 20, Two: 30 } },
  { scoreName: 'communication skills', data: { One: 90, Two: 95 } },
  { scoreName: 'planning skills', data: { One: 90, Two: 95 } },
  { scoreName: 'coding skills', data: { One: 90, Two: 95 } },
];

const getAverageValue = (data: SimpleTableEntry) => {
  let sum = 0;
  let count = 0;
  for (const key in data) {
    if (typeof data[key] === 'number') {
      sum += data[key]; // I checked the data type is number
      count++;
    }
  }
  return sum / count;
};

const colorVariant = (value: number) => {
  if (value > 75) {
    return 'success';
  } else if (value > 25) {
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

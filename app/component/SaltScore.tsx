import {
  Card,
  CardHeader,
  CardBody,
  Accordion,
  AccordionItem,
} from '@nextui-org/react';
import SimpleTable from './SimpleTable';
import { SimpleTableEntry } from '../types';

const scoreData: { scoreName: string; data: SimpleTableEntry }[] = [
  { scoreName: 'weekend test', data: { Week1: 90, Week2: 95 } },
  { scoreName: 'on site tests', data: { One: 90, Two: 95 } },
  { scoreName: 'test interviews', data: { One: 90, Two: 95 } },
  { scoreName: 'communication skills', data: { One: 90, Two: 95 } },
  { scoreName: 'planning skills', data: { One: 90, Two: 95 } },
  { scoreName: 'coding skills', data: { One: 90, Two: 95 } },
];
// const scoreData: { scoreName: string; data: string }[] = [
//   { scoreName: 'Weekendtest', data: 'a' },
//   { scoreName: 'onsitetests', data: 'b' },
//   { scoreName: 'testinterviews', data: 'c' },
//   { scoreName: 'communicationskills', data: 'd' },
//   { scoreName: 'planningskills', data: 'e' },
//   { scoreName: 'codingskills', data: 'f' },
// ];

const SaltScore = () => {
  return (
    <Card shadow="sm" className="my-2">
      <CardBody className="text-small">
        <Accordion>
          {scoreData.map(item => (
            <AccordionItem key={item.scoreName} title={item.scoreName}>
              <SimpleTable data={item.data} />
            </AccordionItem>
          ))}
        </Accordion>
      </CardBody>
    </Card>
  );
};

export default SaltScore;

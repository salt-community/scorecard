import { Card, CardHeader, CardBody } from '@nextui-org/react';
import React from 'react';
import ScoreCardBody from './ScoreCardBody';
import ScoreCardHeader from './ScoreCardHeader';
import { DeveloperData } from '../types';
import { isExcellent } from '../utilities';

interface ScoreCardProps {
  developerData: DeveloperData;
}

const ScoreCard = ({ developerData }: ScoreCardProps) => {
  return (
    <div>
      <Card
        className={`py-4 max-w-sm md:max-w-md ${
          isExcellent(developerData) ? 'border-8 border-purple-600' : ''
        }`}
      >
        <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
          <ScoreCardHeader developerData={developerData} />
        </CardHeader>
        <CardBody className="overflow-visible py-2">
          <ScoreCardBody developerData={developerData} />
        </CardBody>
      </Card>
    </div>
  );
};

export default ScoreCard;

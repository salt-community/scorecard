import { Card, CardHeader, CardBody } from '@nextui-org/react';
import React from 'react';

const ScoreCard = () => {
  return (
    <div>
      <Card className="py-4">
        <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
          <p className="text-tiny uppercase font-bold">Daily Mix</p>
          <small className="text-default-500">12 Tracks</small>
          <h4 className="font-bold text-large">Frontend Radio</h4>
        </CardHeader>
        <CardBody className="overflow-visible py-2">something here</CardBody>
      </Card>
    </div>
  );
};

export default ScoreCard;

import { Card, CardBody, CardFooter, CardHeader } from '@nextui-org/react';
import React from 'react';
import SimpleTable from './SimpleTable';

const ProjectCard = () => {
  return (
    <div>
      <Card shadow="sm">
        <CardHeader>Project 01</CardHeader>
        <CardBody className="text-small">
          <SimpleTable data={{ Commits: 10, Issues: 50 }} />
        </CardBody>
      </Card>
    </div>
  );
};

export default ProjectCard;

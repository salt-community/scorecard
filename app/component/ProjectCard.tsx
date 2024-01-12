import { Card, CardBody, CardFooter, CardHeader } from '@nextui-org/react';
import React from 'react';
import SimpleTable from './SimpleTable';

const ProjectCard = () => {
  return (
    <div>
      <Card shadow="sm" className="my-2">
        <CardHeader className="mt-2">Project 01</CardHeader>
        <CardBody className="text-small">
          <SimpleTable
            data={{
              Commits: 10,
              Issues: 50,
              Duration: '2 week',
              performance: '95%',
              TestCoverage: '90%',
            }}
          />
        </CardBody>
      </Card>
    </div>
  );
};

export default ProjectCard;

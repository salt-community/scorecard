import { Card, CardBody, CardHeader } from '@nextui-org/react';
import React from 'react';
import SimpleTable from './SimpleTable';
import { SimpleTableEntry } from '../types';

interface ProjectCardProps {
  project: { name: string; data: SimpleTableEntry };
}

const ProjectCard = ({ project }: ProjectCardProps) => {
  return (
    <div>
      <Card shadow="sm" className="my-2">
        <CardHeader className="mt-2">{project.name}</CardHeader>
        <CardBody className="text-small">
          <SimpleTable data={project.data} />
        </CardBody>
      </Card>
    </div>
  );
};

export default ProjectCard;

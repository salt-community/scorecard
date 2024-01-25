import { Card, CardBody, CardHeader, Divider } from '@nextui-org/react';
import SimpleTable from './SimpleTable';
import { Project } from '../types';
import Icon from '@/public/icons/Icon';

interface ProjectCardProps {
  project: Project;
}

const ProjectCard = ({ project }: ProjectCardProps) => {
  return (
    <div>
      <Card shadow="sm" className="my-2 p-2">
        <CardHeader className="flex justify-between">
          <p className="font-semibold">{project.name}</p>
          <a href={project.repoUrl} target="_blank">
            <Icon icon="github" className="h-6 w-6 fill-black" />
          </a>
        </CardHeader>
        <Divider />
        <CardBody className="text-small">
          <SimpleTable data={project.data} />
        </CardBody>
      </Card>
    </div>
  );
};

export default ProjectCard;

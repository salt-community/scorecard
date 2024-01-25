import { Project } from '../types';
import ProjectCard from './ProjectCard';

interface ProjectsProps {
  projects: Project[];
}

const Projects = ({ projects }: ProjectsProps) => {
  return (
    <div className="my-4">
      <h4 className="font-bold text-large my-2">Projects</h4>
      {projects.map(project => (
        <ProjectCard key={project.name} project={project} />
      ))}
    </div>
  );
};

export default Projects;

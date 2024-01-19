import ProjectCard from './ProjectCard';

const projects = [
  {
    name: 'Moboga',
    data: {
      Commits: 79,
      Issues: 3,
      Duration: '2 week',
      Performance: '62%',
      TestCoverage: '40%',
    },
  },
  {
    name: 'Talenthub',
    data: {
      Commits: 306,
      Issues: 12,
      Duration: '12 week',
      Performance: '89%',
      TestCoverage: '60%',
    },
  },
];

const Projects = () => {
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

'use client';
import { Image, Divider, Tooltip } from '@nextui-org/react';
import ProjectCard from './ProjectCard';
import SaltScore from './SaltScore';
import Background from './Background';
import Achievements from './Achievements';
import Projects from './Projects';

const userName = 'Finns841594';

const ScoreCardBody = () => {
  return (
    <div className="flex flex-col gap-4">
      <Background />
      <Divider />
      <Achievements />
      <SaltScore />
      <Projects />
    </div>
  );
};

export default ScoreCardBody;

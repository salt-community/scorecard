'use client';
import { Divider } from '@nextui-org/react';
import SaltScore from './SaltScore';
import Background from './Background';
import Achievements from './Achievements';
import Projects from './Projects';

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

'use client';
import { Divider } from '@nextui-org/react';
import SaltScore from './SaltScore';
import Background from './Background';
import Achievements from './Achievements';
import Projects from './Projects';
import { DeveloperData } from '../../types';

interface ScoreCardBodyProps {
  developerData: DeveloperData;
}

const ScoreCardBody = ({ developerData }: ScoreCardBodyProps) => {
  return (
    <div className="flex flex-col gap-4">
      <Background developerBackgroud={developerData.backgroundInformation} />
      <Divider />
      <Achievements userName={developerData.githubUserName} />
      <SaltScore
        scores={developerData.scores}
        radarGraphicData={developerData.radarGraph}
      />
      <Projects projects={developerData.selectedProjects} />
    </div>
  );
};

export default ScoreCardBody;

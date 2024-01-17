'use client';
import { Image, Divider, Tooltip } from '@nextui-org/react';
import {} from '@nextui-org/table';
import React from 'react';
import ProjectCard from './ProjectCard';
import SimpleTable from './SimpleTable';
import SaltScore from './SaltScore';

const userName = 'Finns841594';

const ScoreCardBody = () => {
  return (
    <div>
      <SimpleTable data={{ Commits: 100, Issues: 500 }} />
      <Divider className="my-4" />
      <div className="my-4">
        <h4 className="font-bold text-large my-2">Achievements</h4>
        <Tooltip content="Click to check more on Codewars" placement="top-end">
          <a
            href={`https://www.codewars.com/users/${userName}`}
            target="_blank"
            rel="noopener noreferrer"
          >
            <Image
              alt="codewars budge"
              src={`https://www.codewars.com/users/${userName}/badges/large`}
            />
          </a>
        </Tooltip>
      </div>
      <h4 className="font-bold text-large my-2">Salts Scores</h4>
      <SaltScore />
      <div className="my-4">
        <h4 className="font-bold text-large my-2">Projects</h4>
        <ProjectCard />
        <ProjectCard />s
      </div>
    </div>
  );
};

export default ScoreCardBody;

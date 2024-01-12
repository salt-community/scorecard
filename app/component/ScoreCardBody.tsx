'use client';
import { Image, Divider } from '@nextui-org/react';
import {} from '@nextui-org/table';
import React from 'react';
import ProjectCard from './ProjectCard';
import SimpleTable from './SimpleTable';

const ScoreCardBody = () => {
  return (
    <div>
      <SimpleTable data={{ Commits: 100, Issues: 500 }} />
      <Divider className="my-4" />
      <div className="my-4">
        <h4 className="font-bold text-large my-2">Achievements</h4>
        <Image
          alt="codewars budge"
          src="https://www.codewars.com/users/Finns841594/badges/large"
        />
      </div>
      <div className="my-4">
        <h4 className="font-bold text-large my-2">Projects</h4>
        <ProjectCard />
        <ProjectCard />
      </div>
    </div>
  );
};

export default ScoreCardBody;

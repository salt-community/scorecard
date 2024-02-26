"use client";
import { Divider } from "@nextui-org/react";
import { Developer } from "@/server";

interface ScoreCardBodyProps {
  developer: Developer;
}

const ScoreCardBody = ({ developer }: ScoreCardBodyProps) => {
  return (
    <div className="flex flex-col gap-4">
      <h4>Developer Information</h4>
      <Divider />
      {/*  <SaltScore
        scores={scores}
        radarGraphicData={radarGraph}
        averages={averages}
      /> */}
      <p>Average Backend Score: {developer.averageBackendScore}</p>
      <p>Average Frontend Score: {developer.averageFrontendScore}</p>
    </div>
  );
};

export default ScoreCardBody;

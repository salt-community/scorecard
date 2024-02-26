"use client";
import { Divider } from "@nextui-org/react";
import { Developer } from "@/server";
import SaltScore from "./SaltScore";

interface ScoreCardBodyProps {
  developer: Developer;
}

const ScoreCardBody = ({ developer }: ScoreCardBodyProps) => {
  return (
    <div className="flex flex-col gap-4">
      <h4>Developer Information</h4>
      <Divider />
       <SaltScore
        /* scores={scores}
        radarGraphicData={radarGraph} */
        averageBackendScore={developer.averageBackendScore}
        averageFrontendScore={developer.averageFrontendScore}
      /> 
      <p>Average Backend Score: {developer.averageBackendScore}</p>
      <p>Average Frontend Score: {developer.averageFrontendScore}</p>
    </div>
  );
};

export default ScoreCardBody;

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
      <Divider />
       <SaltScore
        /* scores={scores}
        radarGraphicData={radarGraph} */
        averageBackendScore={developer.averageBackendScore}
        averageFrontendScore={developer.averageFrontendScore}
        developerId={developer.developerId}
      />
    </div>
  );
};

export default ScoreCardBody;

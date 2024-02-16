"use client";
import { Divider } from "@nextui-org/react";
//import SaltScore from "./SaltScore";
import { Developer } from "@/server";
//import Achievements from "./Achievements";
//import Projects from "./Projects";


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
    </div>
  );
};

export default ScoreCardBody;

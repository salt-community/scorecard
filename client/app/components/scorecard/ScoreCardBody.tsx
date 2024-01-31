"use client";
import { Divider } from "@nextui-org/react";
import SaltScore from "./SaltScore";
import Background from "./Background";
import Achievements from "./Achievements";
import Projects from "./Projects";
import { DeveloperData } from "../../types";

interface ScoreCardBodyProps {
  developerData: DeveloperData;
}

const ScoreCardBody = ({ developerData }: ScoreCardBodyProps) => {
  const backgroundInformation = developerData.backgroundInformation;
  const githubUserName = developerData.githubUserName;
  const scores = developerData.scores;
  const radarGraph = developerData.radarGraph;
  const averages = developerData.averages;
  const projects = developerData.selectedProjects;

  return (
    <div className="flex flex-col gap-4">
      <Background developerBackgroud={backgroundInformation} />
      <Divider />
      <Achievements userName={githubUserName} />
      <SaltScore
        scores={scores}
        radarGraphicData={radarGraph}
        averages={averages}
      />
      <Projects projects={projects} />
    </div>
  );
};

export default ScoreCardBody;

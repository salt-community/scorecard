import { DeveloperData, RadarGraphicData } from "@/app/types";
import { isExcellent } from "@/app/utilities";
import { Card, CardHeader } from "@nextui-org/react";
import Grading from "./Grading";
import ScoreboardId from "./ScoreboardId";
import ScoreboardHeader from "./ScoreboardHeader";
import ScoreboardBody from "./ScoreboardBody";

interface ScoreboardProps {
  developerData: DeveloperData;
}

const Scoreboard = ({ developerData }: ScoreboardProps) => {
  return (
    <div className="flex flex-col gap-4">
      <ScoreboardHeader developerData={developerData} />
      <ScoreboardBody />
    </div>
  );
};

export default Scoreboard;

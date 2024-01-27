import { DeveloperData } from "@/app/types";
import ScoreboardHeader from "./ScoreboardHeader";
import ScoreboardBody from "./ScoreboardBody";

interface ScoreboardProps {
  developerData: DeveloperData;
}

const Scoreboard = ({ developerData }: ScoreboardProps) => {
  return (
    <div className="flex flex-col gap-4 h-full">
      <ScoreboardHeader developerData={developerData} />
      <ScoreboardBody scores={developerData.scores} />
    </div>
  );
};

export default Scoreboard;

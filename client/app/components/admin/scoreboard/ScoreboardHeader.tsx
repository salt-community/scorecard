import { DeveloperData, RadarGraphicData } from "@/app/types";
import { isExcellent } from "@/app/utilities";
import { Card, CardHeader } from "@nextui-org/react";
import Grading from "./Grading";
import ScoreboardId from "./ScoreboardId";

interface ScoreboardProps {
  developerData: DeveloperData;
}

const ScoreboardHeader = ({ developerData }: ScoreboardProps) => {
  return (
    <div className="flex flex-row gap-4">
      <Card
        className={`w-72 ${
          isExcellent(developerData) ? "border-8 border-purple-600" : ""
        }`}
      >
        <CardHeader className=" px-4">
          <ScoreboardId developerData={developerData} />
        </CardHeader>
      </Card>
      <Card className=" w-96">
        <Grading />
      </Card>
      <Card className=" flex-1"></Card>
    </div>
  );
};

export default ScoreboardHeader;

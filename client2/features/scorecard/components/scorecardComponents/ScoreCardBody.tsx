"use client";
import { Divider } from "@nextui-org/react";
import { Developer, getScorecardByDeveloperId } from "@/server";
import SaltScore from "./SaltScore";
import { useEffect, useState } from "react";
import { Assignment } from "@/app/components/ListAssignmentsForAccount";

interface ScoreCardBodyProps {
  developer: Developer;
}

const ScoreCardBody = ({ developer }: ScoreCardBodyProps) => {
  const [saltScores, setSaltScores] = useState<Assignment[]>([]);

  useEffect(() => {
    const fetchScoreCard = async () => {
      const response = await getScorecardByDeveloperId(developer.developerId);
      setSaltScores(response.assignmentResponses.assignmentResponseList);
    };
    fetchScoreCard();
  }, []);

  return (
    <div className="flex flex-col gap-4">
      <Divider />
      <SaltScore
        saltScores={saltScores}
        /*radarGraphicData={radarGraph} */
        averageBackendScore={developer.averageBackendScore}
        averageFrontendScore={developer.averageFrontendScore}
      />
    </div>
  );
};

export default ScoreCardBody;

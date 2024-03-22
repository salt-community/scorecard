"use client";
import { Divider } from "@nextui-org/react";
import { Developer, getScorecardByAccountId } from "@/server";
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
      const response = await getScorecardByAccountId(developer.developerId);
      setSaltScores(response.assignmentResponseList);
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

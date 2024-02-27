import { Assignment } from "@/app/components/ListAssignmentsForAccount";
import { getAssignmentsByDeveloperId } from "@/server";
import {
  Card,
  CardHeader,
  CardBody,
  Accordion,
  AccordionItem,
  CircularProgress,
  Chip,
} from "@nextui-org/react";
import ScoreEntry from "./ScoreEntry";
import { useEffect, useState } from "react";
import { RadarGraphic } from "./RadarGraphic";
/* import { Average, RadarGraphicData, Score } from "../../types"; */
/* import { RadarGraphic } from "./RadarGraphic"; */
/* import ScoreEntry from "./ScoreEntry";
import {
  capitalizeEveryWord,
  colorVariant,
  levelVariant,
  scoreData,
} from "@/app/utilities"; */

type DeveloperId = {
  developerId: string;
};

export type Average = {
  developerId: string;
  averageBackendScore: number;
  averageFrontendScore: number;
};

export type RadarGraphicData = {
  subject: string;
  score: number;
};

export function capitalizeEveryWord(inputString: string): string {
  return inputString.replace(/\b\w/g, (match) => match.toUpperCase());
}

export const colorVariant = (value: number) => {
  if (value >= 90) {
    return "secondary";
  } else if (value > 70) {
    return "primary";
  } else {
    return "warning";
  }
};

export const levelVariant = (value: number) => {
  if (value >= 90) {
    return 3;
  } else if (value > 70) {
    return 2;
  } else {
    return 1;
  }
};

const SaltScore = ({
  averageBackendScore,
  averageFrontendScore,
  developerId,
}: Average) => {
  const [assignmentsPerDeveloper, setAssignmentsPerDeveloper] = useState<
    Assignment[]
  >([]);
  const [radarGraphicData, setRadarGraphicData] = useState<RadarGraphicData[]>(
    []
  );
  const totalScoreAverage = Math.round(
    (averageBackendScore + averageFrontendScore) / 2
  );
  var allScores: number[] = [];
  allScores.push(averageBackendScore, averageFrontendScore);

  useEffect(() => {
    const fetchAssignments = async () => {
      try {
        const response = await getAssignmentsByDeveloperId(developerId);
        setAssignmentsPerDeveloper(response);
        setRadarGraphicData(
          assignmentsPerDeveloper.map((assignment) => ({
            subject: assignment.category,
            score: assignment.score,
          }))
        );
      } catch (error) {
        console.error("Error fetching assignments:", error);
      }
    };
    fetchAssignments();
  });

  const backendAssignments: Assignment[] = assignmentsPerDeveloper.filter(
    (assignment) => assignment.category === "BACKEND"
  );

  const frontendAssignments: Assignment[] = assignmentsPerDeveloper.filter(
    (assignment) => assignment.category === "FRONTEND"
  );

  return (
    <>
      <h4 className="font-bold text-large">Salt Scoring</h4>
      <Card shadow="sm">
        <CardHeader className="flex flex-row gap-2">
          <Chip
            color={colorVariant(totalScoreAverage)}
            variant="bordered"
            classNames={{
              content: "drop-shadow shadow-black text-black",
              base: "py-6",
            }}
            startContent={
              <CircularProgress
                size="md"
                value={totalScoreAverage}
                color={colorVariant(totalScoreAverage)}
                showValueLabel={true}
                aria-label="score value"
              />
            }
          >
            <h4 className="text-large mx-2">
              Level {levelVariant(totalScoreAverage)}
            </h4>
          </Chip>
        </CardHeader>
        <CardBody className="text-small">
          <RadarGraphic data={radarGraphicData} />

          <Accordion>
            <AccordionItem
              key={averageBackendScore}
              title={capitalizeEveryWord("Backend")}
              startContent={
                <CircularProgress
                  size="md"
                  value={averageBackendScore}
                  color={colorVariant(averageBackendScore)}
                  showValueLabel={true}
                  aria-label="score value"
                />
              }
            >
              {frontendAssignments.map((assignment, index) => (
                <ScoreEntry key={index} data={assignment} />
              ))}
            </AccordionItem>

            <AccordionItem
              key={averageFrontendScore}
              title={capitalizeEveryWord("Frontend")}
              startContent={
                <CircularProgress
                  size="md"
                  value={averageFrontendScore}
                  color={colorVariant(averageFrontendScore)}
                  showValueLabel={true}
                  aria-label="score value"
                />
              }
            >
              {backendAssignments.map((assignment, index) => (
                <ScoreEntry key={index} data={assignment} />
              ))}
            </AccordionItem>
          </Accordion>
        </CardBody>
      </Card>
    </>
  );
};

export default SaltScore;

"use client";
import { Card, CardHeader, CardBody } from "@nextui-org/react";
import React, { useEffect, useState } from "react";
//import ScoreCardBody from "./ScoreCardBody";
//import { isExcellent } from "@/app/utilities";
import { Developer, getADeveloper } from "@/server";
import ScoreCardBody from "./scorecardComponents/ScoreCardBody";
import ScoreCardHeader from "./scorecardComponents/ScoreCardHeader";

type ScoreCardProps = {
  developerId: string;
};

const ScoreCard = ({ developerId }: { developerId: string }) => {
  const [developer, setDeveloper] = useState<Developer | null>(null);

  useEffect(() => {
    const fetchDeveloper = async () => {
      try {
        const response = await getADeveloper({ developerId });
        setDeveloper(response);
      } catch (error) {
        console.error(`Error getting the developer: ${error}`);
      }
    };

    fetchDeveloper();
  }, [developerId]);

  return (
    <div>
      <Card className={`py-4 max-w-sm md:max-w-md`}>
        <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
          {developer && <ScoreCardHeader developer={developer} />}
        </CardHeader>
        <CardBody className="overflow-visible py-2">
          {developer && <ScoreCardBody developer={developer} />}
        </CardBody>
      </Card>
    </div>
  );
};

export default ScoreCard;

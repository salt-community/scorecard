"use client";
import {
  httpGetAllAssignment,
  httpGetSaltieScoreboard,
} from "@/app/api/request";
import Scoreboard from "@/app/components/admin/scoreboard/Scoreboard";
import { Assignment, SaltieData } from "@/app/types";
import { useEffect, useState } from "react";

export default function Page({ params }: { params: { slug: string } }) {
  const [developer, setDeveloper] = useState<SaltieData>();
  const [assignment, setAssignment] = useState<Assignment[]>();

  const fetchData = async () => {
    const developerData = await httpGetSaltieScoreboard(params.slug);
    setDeveloper(developerData);
    const assignment = await httpGetAllAssignment();
    setAssignment(assignment);
  };

  useEffect(() => {
    fetchData();
    console.log(developer);
  }, []);

  if (!developer) {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }
  return <Scoreboard developer={developer} assignment={assignment!} />;
}

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
  const [loading, setLoading] = useState(true);

  const fetchData = async () => {
    const developerData = await httpGetSaltieScoreboard(params.slug);
    setDeveloper(developerData);
    const assignment = await httpGetAllAssignment();
    setAssignment(assignment);
    setLoading(false);
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (loading == true) {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }

  return <Scoreboard developer={developer!} assignment={assignment!} />;
}

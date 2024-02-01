"use client";

import {
  httpGetAllAssignment,
  httpGetSaltieScoreboard,
} from "@/app/api/request";
import Scoreboard from "@/app/components/admin/scoreboard/Scoreboard";

export default async function Page({ params }: { params: { slug: string } }) {
  const developer = await httpGetSaltieScoreboard(params.slug);
  const assignment = await httpGetAllAssignment();
  if (!developer) {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }
  return <Scoreboard developer={developer} assignment={assignment} />;
}

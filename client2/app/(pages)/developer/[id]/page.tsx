"use client";

import ScoreCard from "@/features/scorecard/components/ScoreCard";
import Link from "next/link";
import React from "react";

const DeveloperPage = ({ params }: { params: { id: string } }) => {
  return (
    <div>
      <h1> Developer with th id: {params.id}</h1>
      <Link href={`/developer/${params.id}/assignments`}>Add asignment</Link>
      <ScoreCard developerId={params.id} />
    </div>
  );
};

export default DeveloperPage;

"use client";

import ScoreCard from "@/features/scorecard/components/ScoreCard";
import Link from "next/link";
import React from "react";

const DeveloperPage = ({ params }: { params: { id: string } }) => {
  return (
          <div className="font-bold	p-6">
      <Link href={`/developer/${params.id}/assignments`}>Add an assignment score</Link>
      {/* <ScoreCard developerId={params.id} /> */}
    </div>
  );
};

export default DeveloperPage;

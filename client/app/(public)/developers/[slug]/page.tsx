"use client";
import { httpGetDeveloperById } from "@/app/api/request";
import ScoreCard from "../../../components/scorecard/ScoreCard";
import { DeveloperData } from "@/app/types";
import { useEffect, useState } from "react";

export default function Page({ params }: { params: { slug: string } }) {
  const [developer, setDeveloper] = useState<DeveloperData>();
  const fetchData = async () => {
    const developerData = await httpGetDeveloperById(params.slug);
    setDeveloper(developerData);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        <ScoreCard developerData={developer!} />
      </div>
    </main>
  );
}

"use client";
import { httpGetAllSaltieScoreboard } from "@/app/api/request";
import ScoreboardList from "@/app/components/admin/ScoreboardList";
import { useEffect, useState } from "react";

export default async function ScoreboardPage() {
  const [developers, setDevelopers] = useState<[]>();
  const fetchData = async () => {
    const developersData = await httpGetAllSaltieScoreboard();
    setDevelopers(developersData);
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (!developers) {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }
  return (
    <div>
      <ScoreboardList salties={developers} />
    </div>
  );
}

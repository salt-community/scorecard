"use client";
import { httpGetAllCoreTeam, httpGetAllDevelopers } from "@/app/api/request";
import { AllDevelopers } from "@/app/components/admin/AllDevelopers";
import { CoreTeam } from "@/app/components/admin/CoreTeam";
import React, { useEffect, useState } from "react";

export default function Core() {
  const [coresTeam, setCoresTeam] = useState<[]>();
  const fetchData = async () => {
    const coresTeam = await httpGetAllCoreTeam();
    setCoresTeam(coresTeam);
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (!coresTeam) {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }
  return (
    <div>
      {" "}
      <CoreTeam coresTeam={coresTeam} />
    </div>
  );
}

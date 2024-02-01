"use client";
import { httpGetAllDevelopers } from "@/app/api/request";
import { AllDevelopers } from "@/app/components/admin/AllDevelopers";
import React, { useEffect, useState } from "react";

export default function Developer() {
  const [developers, setDevelopers] = useState<[]>();
  const fetchData = async () => {
    const developersData = await httpGetAllDevelopers();
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
      <AllDevelopers developers={developers} />
    </div>
  );
}

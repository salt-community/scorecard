import { httpGetAllDevelopers } from "@/app/api/request";
import { AllDevelopers } from "@/app/components/admin/AllDevelopers";
import React from "react";

export default async function developer() {
  const developersData = await httpGetAllDevelopers();
  if (!developersData) {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }
  return (
    <div>
      <AllDevelopers developers={developersData} />
    </div>
  );
}

import { httpGetAllDevelopers } from "@/app/api/request";
import { AllDevelopers } from "@/app/components/admin/AllDevelopers";
import React from "react";

export default async function developer() {
  const developersData = await httpGetAllDevelopers();
  return (
    <div>
      <AllDevelopers developers={developersData} />
    </div>
  );
}

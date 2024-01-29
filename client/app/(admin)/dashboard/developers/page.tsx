import { httpGetAllDevelopers } from "@/app/api/request";
import { AllDevelopers } from "@/app/components/admin/AllDevelopers";
import React from "react";

type developerAdmin = {
  id: string;
  name: string;
  email: string;
  phoneNumber: string;
  role: string;
};

export default async function developer() {
  const developersData = await httpGetAllDevelopers();
  return (
    <div>
      <AllDevelopers developers={developersData} />
    </div>
  );
}

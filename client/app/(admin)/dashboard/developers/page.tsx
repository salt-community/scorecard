import { httpGetAllDevelopers } from "@/app/api/request";
import AllDevelopers from "@/app/components/admin/AllDevelopers";
import React from "react";

type developerAdmin = {
  id: string;
  name: string;
  email: string;
  phoneNumber: string;
  role: string;
};

export default async function DeveloperPage() {
  const developersData: developerAdmin[] = await httpGetAllDevelopers();
  return <AllDevelopers developers={developersData} />;
}

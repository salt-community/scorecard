import { httpGetAllAssignment, httpGetAllDevelopers } from "@/app/api/request";
import AllDevelopers from "@/app/components/admin/AllDevelopers";
import React from "react";

type developerAdmin = {
  id: string;
  name: string;
  email: string;
  phoneNumber: string;
  role: string;
};

const page = async () => {
  const developersData: developerAdmin[] = await httpGetAllDevelopers();
  const assignment: string[] = await httpGetAllAssignment();
  return <AllDevelopers developers={developersData} />;
};

export default page;

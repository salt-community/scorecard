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

const DevelopersPage = async () => {
  const developersData: developerAdmin[] = await httpGetAllDevelopers();
  return <AllDevelopers developers={developersData} />;
};

export default DevelopersPage;

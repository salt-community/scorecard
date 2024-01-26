import { httpGetAllDevelopers } from "@/app/api/request";
import AllDevelopers from "@/app/components/admin/AllDevelopers";
import React from "react";

type developerInList = {
  id: string;
  name: string;
  profilePicture: string;
  standoutIntro: string;
};

const page = async () => {
  const developersData: developerInList[] = await httpGetAllDevelopers();
  return <AllDevelopers developers={developersData} />;
};

export default page;

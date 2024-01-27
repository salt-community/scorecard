import { httpGetAllSaltieScoreboard } from "@/app/api/request";
import Scoreboard from "@/app/components/admin/Scoreboard";
import { RadarGraphicData } from "@/app/types";

type saltieAdmin = {
  id: string;
  name: string;
  radarGraph: RadarGraphicData[];
};

const page = async () => {
  const developersData: saltieAdmin[] = await httpGetAllSaltieScoreboard();
  return <Scoreboard salties={developersData} />;
};

export default page;

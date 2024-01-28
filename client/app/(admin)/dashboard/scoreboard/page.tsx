import { httpGetAllSaltieScoreboard } from "@/app/api/request";
import ScoreboardList from "@/app/components/admin/ScoreboardList";
import { RadarGraphicData } from "@/app/types";

type saltieAdmin = {
  id: string;
  name: string;
  radarGraph: RadarGraphicData[];
};

const page = async () => {
  const developersData: saltieAdmin[] = await httpGetAllSaltieScoreboard();
  return <ScoreboardList salties={developersData} />;
};

export default page;

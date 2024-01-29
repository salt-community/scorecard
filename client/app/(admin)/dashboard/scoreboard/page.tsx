import { httpGetAllSaltieScoreboard } from "@/app/api/request";
import ScoreboardList from "@/app/components/admin/ScoreboardList";
import { RadarGraphicData } from "@/app/types";

type saltieAdmin = {
  id: string;
  name: string;
  radarGraph: RadarGraphicData[];
};

export default async function ScoreboardPage() {
  const developersData: saltieAdmin[] = await httpGetAllSaltieScoreboard();
  return <div>{/* <ScoreboardList salties={developersData} /> */}</div>;
}

import { httpGetAllSaltieScoreboard } from "@/app/api/request";
import ScoreboardList from "@/app/components/admin/ScoreboardList";

export default async function ScoreboardPage() {
  const developersData = await httpGetAllSaltieScoreboard();
  if (!developersData) {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }
  return (
    <div>
      <ScoreboardList salties={developersData} />
    </div>
  );
}

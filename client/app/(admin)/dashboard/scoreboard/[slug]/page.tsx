import { httpGetDeveloperById } from "@/app/api/request";
import Scoreboard from "@/app/components/admin/scoreboard/Scoreboard";

export default async function Page({ params }: { params: { slug: string } }) {
  const developerData = await httpGetDeveloperById(params.slug);

  return <Scoreboard developerData={developerData} />;
}

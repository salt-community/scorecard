import { httpGetDeveloperById } from "@/app/api/request";
import ScoreCard from "../../../components/scorecard/ScoreCard";

export default async function Page({ params }: { params: { slug: string } }) {
  const developerData = await httpGetDeveloperById(params.slug);

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        <ScoreCard developerData={developerData} />
      </div>
    </main>
  );
}

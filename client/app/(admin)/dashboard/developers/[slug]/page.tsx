import { httpGetDeveloperById } from "@/app/api/request";
import ScoreCard from "@/app/components/scorecard/ScoreCard";

export default async function DeveloperDetailPage({
  params,
}: {
  params: { slug: string };
}) {
  const developerData = await httpGetDeveloperById(params.slug);

  return (
    <main className="ml-[300px] absolute right-0 w-full max-w-[calc(100vw-19rem)] top-20 max-h-[calc(100vh-5rem)] p-4 shadow-xl shadow-blue-gray-900/5 border-solid border-2">
      <div className="overflow-y-auto flex flex-col md:flex-row md:gap-5">
        <ScoreCard developerData={developerData} />
      </div>
    </main>
  );
}

import { httpGetDeveloperById } from "@/app/api/request";
import ScoreCard from "../../../components/scorecard/ScoreCard";

export default async function Page({ params }: { params: { slug: string } }) {
  const BASIC_URI = process.env.NEXT_PUBLIC_API_URL;

  const httpGetDeveloperById = async (id: string) => {
    const response = await fetch(`${BASIC_URI}/api/developers/${id}`, {
      cache: "no-cache",
    });
    return await response.json();
  };
  const developerData = await httpGetDeveloperById(params.slug);

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        <ScoreCard developerData={developerData} />
      </div>
    </main>
  );
}

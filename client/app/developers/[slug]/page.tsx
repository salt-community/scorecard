import ScoreCard from '../../components/ScoreCard';
import { sampleGeneratedDeveloperData } from '../../sampleData';

// should apply TanStack Query for fetching
const apiUrl = process.env.NEXT_PUBLIC_API_URL;
const fetchUserByAccountId = async (id: string) => {
  const res = await fetch(`https://scorecard-l6oa.onrender.com/api/developers/` + id, {
    cache: "no-cache"
  });
  return res.json();
};

export default async function Page({ params }: { params: { slug: string } }) {
  const developerData = await fetchUserByAccountId(params.slug);
  console.log(developerData);
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        <ScoreCard
          developerData={
            developerData
          }
        />
      </div>
    </main>
  );
}

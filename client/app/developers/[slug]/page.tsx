import ScoreCard from '../../components/ScoreCard';

const fetchUserByAccountId = async (id: string) => {
  const res = await fetch('http://localhost:8080/api/accounts/' + id);
  return res.json();
};

export default async function Page({ params }: { params: { slug: string } }) {
  const developerData = await fetchUserByAccountId(params.slug);

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        <p>Hello developer</p>
        <ScoreCard developerData={developerData} />
      </div>
    </main>
  );
}

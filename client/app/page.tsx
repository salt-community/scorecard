import ScoreCard from './component/ScoreCard';
import { sampleGeneratedDeveloperData } from './sampleData';

const fetchAllUsers = async () => {
  const res = await fetch('http://localhost:8080/api/account');
  return res.json();
};

export default async function Home() {
  const developersData = await fetchAllUsers();

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        {developersData.map(item => (
          <p>{item.id}</p>
        ))}
      </div>
    </main>
  );
}

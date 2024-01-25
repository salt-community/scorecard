import ScoreCard from '../components/ScoreCard';
import { sampleGeneratedDeveloperData } from '../sampleData';

// const fetchUserByAccountId = async (id: string) => {
//   const res = await fetch(
//     'http://localhost:8080/api/account/70694d26-adeb-4ea2-b39c-80e0bd950973'
//   );
//   return res.json();
// };

export default async function Home() {
  // const developerData = await fetchUserByAccountId('');
  // console.log('developerData: ', developerData);

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        <p>Hello developer</p>
        <ScoreCard developerData={sampleGeneratedDeveloperData} />
      </div>
    </main>
  );
}

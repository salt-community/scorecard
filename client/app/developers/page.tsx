import ScoreCard from '../components/ScoreCard';
import { sampleGeneratedDeveloperData } from '../sampleData';

export default async function Page() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        <p>Showing default developer as an example</p>
        <ScoreCard developerData={sampleGeneratedDeveloperData} />
      </div>
    </main>
  );
}

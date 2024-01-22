import ScoreCard from './component/ScoreCard';
import { sampleGeneratedDeveloperData } from './sampleData';

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        <ScoreCard developerData={sampleGeneratedDeveloperData} />
      </div>
    </main>
  );
}

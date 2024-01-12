import ScoreCard from './component/ScoreCard';

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <div>
        <ScoreCard />
      </div>
    </main>
  );
}

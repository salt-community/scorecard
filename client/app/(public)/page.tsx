import Link from "next/link";
import { ShowCard } from "../components/DeveloperCard";
import { Banner } from "../components/Banner";

// should apply TanStack Query for fetching
const apiUrl = process.env.NEXT_PUBLIC_API_URL;
const fetchAllUsers = async () => {
  const res = await fetch(
    `https://scorecard-server.onrender.com/api/developers`,
    {
      cache: "no-cache",
    }
  );
  const data = await res.json();
  return data;
};

type developerInList = {
  id: string;
  name: string;
  profilePicture: string;
  standoutIntro: string;
};

export default async function Home() {
  const developersData = await fetchAllUsers();
  return (
    <main className="flex min-h-screen flex-col items-center p-0 md:p-24">
      <Banner />
      <div className="w-full px-2">
        {developersData.map((item: developerInList) => (
          <Link href={`/developers/${item.id}`} key={item.id}>
            <ShowCard
              id={item.id}
              name={item.name}
              profilePicture={item.profilePicture}
              standoutIntro={item.standoutIntro}
            />
          </Link>
        ))}
      </div>
    </main>
  );
}

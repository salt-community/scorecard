import Link from "next/link";
import { Banner } from "../components/Banner";
import { httpGetAllAccounts } from "../api/request";
import { DeveloperCard } from "../components/DeveloperCard";

type developerInList = {
  id: string;
  name: string;
  profilePicture: string;
  standoutIntro: string;
};

export default async function Home() {
  const developersData = await httpGetAllAccounts();
  return (
    <main className="flex min-h-screen flex-col items-center p-0 md:p-24">
      <Banner />
      <div className="w-full px-2">
        {developersData.map((item: developerInList) => (
          <Link href={`/developers/${item.id}`} key={item.id}>
            <DeveloperCard
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

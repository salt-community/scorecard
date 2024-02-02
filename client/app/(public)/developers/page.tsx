"use client";
import { httpGetAllAccounts } from "@/app/api/request";
import { DeveloperCard } from "@/app/components/DeveloperCard";
import Link from "next/link";
import { useEffect, useState } from "react";

export type developerInList = {
  id: string;
  name: string;
  profilePicture: string;
  standoutIntro: string;
};

export default function Home() {
  const [developers, setDevelopers] = useState<developerInList[]>();
  const fetchData = async () => {
    const developersData = await httpGetAllAccounts();
    setDevelopers(developersData);
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (!developers) {
    return <h1>Loading ...</h1>;
  } else {
    return (
      <main className="flex min-h-screen flex-col items-start justify-start py-12 px-24">
        <div className="w-full px-2">
          {developers?.map((item: developerInList) => (
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
}

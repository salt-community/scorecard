"use client";
import { httpGetAllAccounts } from "@/app/api/request";
import { DeveloperCard } from "@/app/components/DeveloperCard";
import WebHeader from "@/app/components/WebHeader";
import Link from "next/link";
import { useEffect, useState } from "react";
import { useCookies } from "next-client-cookies";
import { useRouter } from "next/navigation";

export type developerInList = {
  id: string;
  name: string;
  profilePicture: string;
  standoutIntro: string;
};

export default function Home() {
  const [developers, setDevelopers] = useState<developerInList[]>();
  const [loading, setLoading] = useState(true);
  const cookies = useCookies();
  const router = useRouter();

  const fetchData = async () => {
    const developersData = await httpGetAllAccounts();
    setDevelopers(developersData);
    setLoading(false);
  };

  const checkRole = () => {
    const role = cookies.get("salt_role");
    if (role != "core") {
      router.push("/login");
    }
  };

  useEffect(() => {
    checkRole();
    fetchData();
  }, []);

  if (loading == true) {
    return <h1>Loading ...</h1>;
  } else {
    return (
      <div>
        <WebHeader />
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
      </div>
    );
  }
}

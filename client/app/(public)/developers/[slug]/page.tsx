"use client";
import { httpGetDeveloperById } from "@/app/api/request";
import ScoreCard from "../../../components/scorecard/ScoreCard";
import { DeveloperData } from "@/app/types";
import { useEffect, useState } from "react";
import WebHeader from "@/app/components/WebHeader";
import { useCookies } from "next-client-cookies";
import { useRouter } from "next/navigation";

export default function Page({ params }: { params: { slug: string } }) {
  const [developer, setDeveloper] = useState<DeveloperData>();
  const [loading, setLoading] = useState(true);
  const cookies = useCookies();
  const router = useRouter();

  const fetchData = async () => {
    const developerData = await httpGetDeveloperById(params.slug);
    setDeveloper(developerData);
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

  if (loading == true || cookies.get("salt_role") != "core") {
    return <h1>Loading ...</h1>;
  } else {
    return (
      <div>
        <WebHeader />
        <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
          <div>
            <ScoreCard developerData={developer!} />
          </div>
        </main>
      </div>
    );
  }
}

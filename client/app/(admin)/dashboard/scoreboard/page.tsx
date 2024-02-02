"use client";
import { httpGetAllSaltieScoreboard } from "@/app/api/request";
import ScoreboardList from "@/app/components/admin/ScoreboardList";
import { useEffect, useState } from "react";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";

export default function ScoreboardPage() {
  const [developers, setDevelopers] = useState<[]>();
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  const fetchData = async () => {
    const developersData = await httpGetAllSaltieScoreboard();
    setDevelopers(developersData);
    setLoading(false);
  };

  const checkRole = () => {
    const role = Cookies.get("salt_role");
    if (role != "core") {
      router.push("/login");
    }
  };

  useEffect(() => {
    checkRole();
    fetchData();
  }, []);

  if (loading == true) {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }
  return (
    <div>
      <ScoreboardList salties={developers!} />
    </div>
  );
}

"use client";
import { httpGetAllCoreTeam } from "@/app/api/request";
import { CoreTeam } from "@/app/components/admin/CoreTeam";
import React, { useEffect, useState } from "react";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";

export default function Core() {
  const [coresTeam, setCoresTeam] = useState<[]>();
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  const checkRole = () => {
    const role = Cookies.get("salt_role");
    if (role != "core") {
      router.push("/login");
    }
  };

  const fetchData = async () => {
    const coresTeam = await httpGetAllCoreTeam();
    setCoresTeam(coresTeam);
    setLoading(false);
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
      <CoreTeam coresTeam={coresTeam!} />
    </div>
  );
}

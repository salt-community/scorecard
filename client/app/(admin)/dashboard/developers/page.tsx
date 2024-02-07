"use client";
import { httpGetAllDevelopers } from "@/app/api/request";
import { AllDevelopers } from "@/app/components/admin/AllDevelopers";
import React, { useEffect, useState } from "react";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";

export default function Developer() {
  const [developers, setDevelopers] = useState<[]>();
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  const fetchData = async () => {
    const developersData = await httpGetAllDevelopers();
    setDevelopers(developersData);
    setLoading(false);
  };

  /*   const checkRole = () => {
    const role = Cookies.get("salt_role");
    if (role != "core") {
      router.push("/login");
    }
  }; */

  useEffect(() => {
    //checkRole();
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
      <AllDevelopers developers={developers!} />
    </div>
  );
}

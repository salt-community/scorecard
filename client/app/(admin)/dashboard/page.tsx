"use client";
import React, { useEffect } from "react";
import { useCookies } from "next-client-cookies";
import { useRouter } from "next/navigation";

export default function Dashboard() {
  const cookies = useCookies();
  const router = useRouter();

  const checkRole = () => {
    const role = cookies.get("salt_role");
    if (role != "core") {
      router.push("/login");
    }
  };

  useEffect(() => {
    checkRole();
  }, []);
  return <div>Dashboard page</div>;
}

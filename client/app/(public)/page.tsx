"use client";
import { useCookies } from "next-client-cookies";
import { useRouter } from "next/navigation";

export default function Home() {
  const cookies = useCookies();
  const router = useRouter();

  const role = cookies.get("salt_role");

  if (role == "core") {
    router.push("/developers");
  } else {
    router.push("/login");
  }
}

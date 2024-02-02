"use client";
import { Link, Navbar, NavbarBrand } from "@nextui-org/react";
import { useRouter } from "next/navigation";
import Cookies from "js-cookie";

const WebHeader = () => {
  const router = useRouter();

  const handleLogOut = () => {
    Cookies.remove("salt_role", { path: "/" });
    router.push("/login");
  };
  return (
    <div className="w-full flex justify-center ">
      <Navbar className="flex flex-row justify-center">
        <Link href={"/developers"} color="foreground">
          <NavbarBrand>
            <h4 className="font-bold text-2xl text-inherit">{"</salt>"}</h4>
          </NavbarBrand>
        </Link>
        <div className="flex flex-row gap-4">
          <a href="/dashboard">
            <button
              className=" bg-slate-400 hover:bg-accent text-white font-bold py-2 px-4 rounded"
              onClick={() => handleLogOut}
            >
              Dashboard
            </button>
          </a>
          <button
            className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
            onClick={() => handleLogOut()}
          >
            Log Out
          </button>
        </div>
      </Navbar>
    </div>
  );
};

export default WebHeader;

"use client";
import { Link, Navbar, NavbarBrand } from "@nextui-org/react";
import { useCookies } from "next-client-cookies";
import { useRouter } from "next/navigation";

const WebHeader = () => {
  const cookies = useCookies();
  const router = useRouter();

  const handleLogOut = () => {
    cookies.remove("salt_role");
    router.push("/");
  };
  return (
    <div className="w-full flex justify-center ">
      <Navbar className="flex flex-row justify-center">
        <Link href={"/developers"} color="foreground">
          <NavbarBrand>
            <h4 className="font-bold text-2xl text-inherit">{"</salt>"}</h4>
          </NavbarBrand>
        </Link>
        <a href="/login">
          <button
            className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
            onClick={() => handleLogOut}
          >
            Logout
          </button>
        </a>
      </Navbar>
    </div>
  );
};

export default WebHeader;

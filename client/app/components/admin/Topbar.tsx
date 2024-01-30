"use client";
import { Button, Card, Typography } from "@material-tailwind/react";
import Link from "next/link";
import { usePathname } from "next/navigation";
import React from "react";

const Topbar = () => {
  const pathname = usePathname();
  const path = pathname.split("/");
  let label = path[path.length - 1];

  const topLabel = ["dashboard", "developers", "scoreboard"];
  if (!topLabel.includes(label)) {
    label = "developer";
  }

  return (
    <Card
      className="flex flex-row justify-between p-4 w-full h-16  pl-4 shadow-xl shadow-blue-gray-900/5 border-l-2 border-b-2 border-r-2"
      placeholder={undefined}
    >
      <Typography variant="h2" className="text-accent" placeholder={undefined}>
        {label.charAt(0).toUpperCase() + label.slice(1)}
      </Typography>
      <Link href={`/dashboard/add-developer`} className="">
        <Button
          className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
          placeholder={undefined}
        >
          Add Developer
        </Button>
      </Link>
    </Card>
  );
};

export default Topbar;

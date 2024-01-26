"use client";
import { Card, Typography } from "@material-tailwind/react";
import { usePathname } from "next/navigation";
import React from "react";

const Topbar = () => {
  const pathname = usePathname();
  const path = pathname.split("/");
  let label = path[path.length - 1];

  const topLabel = ["dashboard", "developers"];
  if (!topLabel.includes(label)) {
    label = "developer";
  }

  return (
    <Card
      className="w-full h-16  pl-4 shadow-xl shadow-blue-gray-900/5 border-l-2 border-b-2 border-r-2"
      placeholder={undefined}
    >
      <Typography
        variant="h2"
        className=" my-auto text-accent"
        placeholder={undefined}
      >
        {label.charAt(0).toUpperCase() + label.slice(1)}
      </Typography>
    </Card>
  );
};

export default Topbar;

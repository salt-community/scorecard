"use client";
import React from "react";
import { Card, List, ListItem, ListItemPrefix } from "@material-tailwind/react";
import {
  PresentationChartBarIcon,
  UsersIcon,
  PowerIcon,
} from "@heroicons/react/24/solid";
import { usePathname } from "next/navigation";
import Image from "next/image";

export function Sidebar() {
  const [open, setOpen] = React.useState(1);
  const pathname = usePathname();

  const handleOpen = (value: React.SetStateAction<number>) => {
    setOpen(open === value ? 0 : value);
  };

  return (
    <Card
      className="h-screen w-screen max-w-[18rem] p-4 shadow-xl shadow-blue-gray-900/5 border-r-2 border-l-2"
      placeholder={undefined}
    >
      <div className="mb-4 px-4">
        <Image src="/saltLogo.png" width={150} height={200} alt="logo" />
      </div>
      <List placeholder={undefined}>
        <a href="/dashboard">
          <ListItem placeholder={undefined}>
            <ListItemPrefix placeholder={undefined}>
              <PresentationChartBarIcon className="h-5 w-5" />
            </ListItemPrefix>
            Dashboard
          </ListItem>
        </a>
        <a href="/dashboard/developers">
          <ListItem placeholder={undefined}>
            <ListItemPrefix placeholder={undefined}>
              <UsersIcon className="h-5 w-5" />
            </ListItemPrefix>
            Developers
          </ListItem>
        </a>
        <hr className="my-2 border-blue-gray-50" />
        <ListItem placeholder={undefined}>
          <ListItemPrefix placeholder={undefined}>
            <PowerIcon className="h-5 w-5" />
          </ListItemPrefix>
          <a href="/api/auth/logout"> Log Out</a>
        </ListItem>
      </List>
    </Card>
  );
}

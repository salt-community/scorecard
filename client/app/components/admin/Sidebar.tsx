"use client";
import React from "react";
import {
  Card,
  Typography,
  List,
  ListItem,
  ListItemPrefix,
} from "@material-tailwind/react";
import {
  PresentationChartBarIcon,
  UsersIcon,
  PowerIcon,
  AcademicCapIcon,
} from "@heroicons/react/24/solid";

export function Sidebar() {
  return (
    <Card
      className="h-screen w-screen max-w-[18rem] p-4 shadow-xl shadow-blue-gray-900/5 border-r-2 border-l-2"
      placeholder={undefined}
    >
      <div className="mb-4 px-4 font-bold">
        <a href="/developers">
          <Typography variant="h2" color="blue-gray" placeholder={undefined}>
            {"</salt>"}
          </Typography>
        </a>
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
        <a href="/dashboard/core-team">
          <ListItem placeholder={undefined}>
            <ListItemPrefix placeholder={undefined}>
              <UsersIcon className="h-5 w-5" />
            </ListItemPrefix>
            Core Team
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
        <a href="/dashboard/scoreboard">
          <ListItem placeholder={undefined}>
            <ListItemPrefix placeholder={undefined}>
              <AcademicCapIcon className="h-5 w-5" />
            </ListItemPrefix>
            Scoreboard
          </ListItem>
        </a>
        <hr className="my-2 border-blue-gray-50" />
        <ListItem placeholder={undefined}>
          <ListItemPrefix placeholder={undefined}>
            <PowerIcon className="h-5 w-5" />
          </ListItemPrefix>
          <a href=""> Log Out</a>
        </ListItem>
      </List>
    </Card>
  );
}

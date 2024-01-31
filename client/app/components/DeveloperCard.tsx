import React from "react";
import { Card, CardHeader, Avatar } from "@nextui-org/react";

type DeveloperCardProps = {
  id: string;
  name: string;
  profilePicture: string;
  standoutIntro: string;
};

export const DeveloperCard = ({ name, profilePicture }: DeveloperCardProps) => {
  return (
    <Card className="my-3">
      <CardHeader className="flex flex-col">
        <div className="flex gap-5 min-w-80">
          <Avatar isBordered radius="full" size="lg" src={profilePicture} />
          <div className="flex flex-col gap-1 items-start justify-center">
            <h4 className="text-md font-semibold leading-none text-default-600">
              {name}
            </h4>
          </div>
        </div>
      </CardHeader>
    </Card>
  );
};

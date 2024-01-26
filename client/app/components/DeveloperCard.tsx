import React from 'react'
import {Card, CardHeader, CardBody, CardFooter, Avatar} from "@nextui-org/react";
type showCardProps = {
    id: string;
    name: string;
    profilePicture: string;
    standoutIntro: string;
  };

export const ShowCard = ({name, profilePicture, standoutIntro}:showCardProps) => {
  return (
      <Card className="my-3">
      <CardHeader className="flex flex-col">
        <div className="flex gap-5 min-w-80">
          <Avatar isBordered radius="full" size="lg" src={profilePicture} />
          <div className="flex flex-col gap-1 items-start justify-center">
            <h4 className="text-md font-semibold leading-none text-default-600">{name}</h4>
            {/* <h5 className="text-md truncate tracking-tight text-default-400">{standoutIntro}</h5> */}
          </div>
        </div>

      </CardHeader>
    </Card>
  )
}

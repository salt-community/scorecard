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
    <div>
      <Card className="max-w-[340px]">
      <CardHeader className="justify-between">
        <div className="flex gap-5">
          <Avatar isBordered radius="full" size="md" src={profilePicture} />
          <div className="flex flex-col gap-1 items-start justify-center">
            <h4 className="text-small font-semibold leading-none text-default-600">{name}</h4>
            <h5 className="text-small tracking-tight text-default-400">{standoutIntro}</h5>
          </div>
        </div>

      </CardHeader>
    </Card>
    </div>
  )
}

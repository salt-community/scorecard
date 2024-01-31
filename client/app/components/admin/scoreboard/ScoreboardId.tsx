"use client";
import { Avatar } from "@nextui-org/react";
import ContactsBar from "../../scorecard/ContactsBar";
import { SaltieData } from "@/app/types";

type ScoreboardIdProps = {
  developer: SaltieData;
};

const ScoreboardId = ({ developer }: ScoreboardIdProps) => {
  const avatarUrl = developer.githubProfilePictureUrl;
  const developerName = developer.name;
  const programmingLanguage = developer.bootcamp;
  const github = developer.githubUrl;
  const linkedin = developer.linkedinUrl;
  return (
    <div className="flex w-full gap-8">
      <Avatar
        isBordered
        color="default"
        src={avatarUrl}
        className="h-full text-large w-full"
        name={developerName}
      />
      <div className="flex flex-col my-auto w-[310px] md:w-72">
        <p className="text-tiny uppercase font-bold">
          {programmingLanguage ? programmingLanguage.toUpperCase() : ""}{" "}
          Developer
        </p>
        <h4 className="font-bold text-large">{developerName}</h4>
        <ContactsBar githubUrl={github} linkedinUrl={linkedin} />
      </div>
    </div>
  );
};

export default ScoreboardId;

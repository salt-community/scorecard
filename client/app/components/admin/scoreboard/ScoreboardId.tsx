import { Avatar } from "@nextui-org/react";
import ContactsBar from "../../scorecard/ContactsBar";
import { DeveloperData } from "@/app/types";

interface ScoreboardHeaderProps {
  developerData: DeveloperData;
}

const ScoreboardId = ({ developerData }: ScoreboardHeaderProps) => {
  const avatarUrl = developerData.githubProfilePictureUrl;
  const developerName = developerData.name;
  const programmingLanguage = developerData.bootcamp;
  const standoutIntro = developerData.standoutIntro;
  const github = developerData.githubUrl;
  const linkedin = developerData.linkedinUrl;
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
          {programmingLanguage.toUpperCase()} Developer
        </p>
        <h4 className="font-bold text-large">{developerName}</h4>
        <ContactsBar githubUrl={github} linkedinUrl={linkedin} />
      </div>
    </div>
  );
};

export default ScoreboardId;

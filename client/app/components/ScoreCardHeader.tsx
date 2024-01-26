import { Avatar } from '@nextui-org/react';
import ContactsBar from './ContactsBar';
import { DeveloperData } from '../types';

interface ScoreCardHeaderProps {
  developerData: DeveloperData;
}

const ScoreCardHeader = ({ developerData }: ScoreCardHeaderProps) => {
  const avatarUrl = developerData.githubProfilePictureUrl;
  const developerName = developerData.name;
  const programmingLanguage = developerData.bootcamp;
  const standoutIntro = developerData.standoutIntro;
  const github = developerData.githubUrl;
  const linkedin = developerData.linkedinUrl;
  return (
    <div className="grid grid-cols-1 md:flex md:w-full gap-4 md:gap-8 md:my-4">
      <Avatar
        isBordered
        color="default"
        src={avatarUrl}
        className="w-20 h-20 text-large"
        name={developerName}
      />
      <div className="flex flex-col my-auto w-[310px] md:w-72">
        <p className="text-tiny uppercase font-bold">
          {programmingLanguage.toUpperCase()} Developer
        </p>
        <div className="flex flex-row justify-between">
          <h4 className="font-bold text-large">{developerName}</h4>
          <ContactsBar githubUrl={github} linkedinUrl={linkedin} />
        </div>
        <small className="text-default-500">{standoutIntro}</small>
      </div>
    </div>
  );
};

export default ScoreCardHeader;

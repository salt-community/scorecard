import { Avatar } from '@nextui-org/react';
import { Developer } from '@/server';
import ContactsBar from './ContactsBar';

interface ScoreCardHeaderProps {
  developer: Developer;
}

const ScoreCardHeader = ({ developer }: ScoreCardHeaderProps) => {
  //const avatarUrl = developerData.githubProfilePictureUrl;
  const developerName = developer.firstName + " " + developer.lastName;
  const programmingLanguage = developer.bootcampCourse;
  const email = developer.emailAddress;
  //const linkedin = developerData.linkedinUrl;
  return (
    <>
    {developer &&
    <div className="grid grid-cols-1 md:flex md:w-full gap-4 md:gap-8 md:my-4">
      <Avatar
        isBordered
        color="default"
        //src={avatarUrl}
        className="w-20 h-20 text-large bg-slate-950"
        name={developerName}
      />
      <div className="flex flex-col my-auto w-[310px] md:w-72">
        <p className="text-tiny uppercase font-bold">
          {programmingLanguage.toUpperCase()} Developer
        </p>
        <div className="flex flex-row justify-between">
          <h4 className="font-bold text-large">{developerName}</h4>
           <ContactsBar email={email}/> 
        </div>
        <small className="text-default-500">Amazing developer</small>
      </div>
    </div>
    }
    </>
  );
};

export default ScoreCardHeader;

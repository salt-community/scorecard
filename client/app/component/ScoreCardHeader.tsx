import { Avatar } from '@nextui-org/react';
import ContactsBar from './ContactsBar';

interface ScoreCardHeaderProps {
  developerInfo: {
    avatarUrl: string;
    developerName: string;
    programmingLanguage: string;
    standoutIntro: string;
  };
}

// const ScoreCardHeader = ({ developerInfo }: ScoreCardHeaderProps) => {
const ScoreCardHeader = () => {
  const avatarUrl = 'https://avatars.githubusercontent.com/u/65496134?v=4';
  const developerName = 'Feng Yang';
  const programmingLanguage = 'javascript';
  const standoutIntro = 'Experienced in various of client projects';

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
          <ContactsBar />
        </div>
        <small className="text-default-500">{standoutIntro}</small>
      </div>
    </div>
  );
};

export default ScoreCardHeader;

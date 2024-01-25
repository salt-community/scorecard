import { Tooltip, Image } from '@nextui-org/react';

interface AchievementsProps {
  userName: string;
}

const Achievements = ({ userName }: AchievementsProps) => {
  return (
    <div>
      <h4 className="font-bold text-large my-2">Achievements</h4>
      <Tooltip content="Click to check more on Codewars" placement="top-end">
        <a
          href={`https://www.codewars.com/users/${userName}`}
          target="_blank"
          rel="noopener noreferrer"
        >
          <Image
            alt="codewars budge"
            src={`https://www.codewars.com/users/${userName}/badges/large`}
          />
        </a>
      </Tooltip>
    </div>
  );
};

export default Achievements;

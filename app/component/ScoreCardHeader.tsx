import { Avatar } from '@nextui-org/react';

const avatar_url = 'https://avatars.githubusercontent.com/u/65496134?v=4';
const developer_name = 'Feng Yang';
const programming_language = 'javascript';
const standout_intro = 'Experienced in various of client projects';

const ScoreCardHeader = () => {
  return (
    <div className="grid grid-cols-1 md:flex gap-4 md:gap-8 md:my-4">
      <Avatar
        isBordered
        color="default"
        src={avatar_url}
        className="w-20 h-20 text-large"
        name={developer_name}
      />
      <div className="flex flex-col my-auto">
        <p className="text-tiny uppercase font-bold">
          {programming_language.toUpperCase()} Developer
        </p>
        <h4 className="font-bold text-large">{developer_name}</h4>
        <small className="text-default-500">{standout_intro}</small>
      </div>
    </div>
  );
};

export default ScoreCardHeader;

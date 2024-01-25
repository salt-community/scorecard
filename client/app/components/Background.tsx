import { BackgroundInformation } from '../types';
import SimpleTable from './SimpleTable';

const valueGeneretor = (values: string[]) => {
  const valueString = values.join(', ');
  return <h5 className="text-end">{valueString}</h5>;
};

interface BackgroundProps {
  developerBackgroud: BackgroundInformation;
}

const Background = ({ developerBackgroud }: BackgroundProps) => {
  return (
    <div className="flex flex-col px-2 md:px-4">
      <div className="flex flex-row justify-between my-1">
        <h5 className="font-bold">Nationalities</h5>
        {valueGeneretor(developerBackgroud.nationalities)}
      </div>
      <div className="flex flex-row justify-between my-1">
        <h5 className="font-bold">Languages</h5>
        <SimpleTable data={developerBackgroud.spokenLanguages} />
      </div>
      <div className="flex flex-row justify-between my-1">
        <h5 className="font-bold">Educations</h5>
        {developerBackgroud.academic.major}
      </div>
      <div className="flex flex-row justify-between my-1">
        <h5 className="font-bold">Skills</h5>
        {valueGeneretor(developerBackgroud.skills)}
      </div>
    </div>
  );
};

export default Background;

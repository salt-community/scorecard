const SampleData = {
  nationalities: ['Chinese'],
  spokenLanguages: ['Chinese', 'English', 'Swedish'],
  educations: ['Architecture'],
  skills: [
    'JavaScript',
    'TypeScript',
    'React',
    'Next.js',
    'Node.js',
    'Express',
    'MongoDB',
  ],
};

const valueGeneretor = (values: string[]) => {
  const valueString = values.join(', ');
  return <h5 className="text-end">{valueString}</h5>;
};

const Background = () => {
  return (
    <div className="flex flex-col px-2 md:px-4">
      <div className="flex flex-row justify-between my-1">
        <h5 className="font-bold">Nationalities</h5>
        {valueGeneretor(SampleData.nationalities)}
      </div>
      <div className="flex flex-row justify-between my-1">
        <h5 className="font-bold">Languages</h5>
        {valueGeneretor(SampleData.spokenLanguages)}
      </div>
      <div className="flex flex-row justify-between my-1">
        <h5 className="font-bold">Educations</h5>
        {valueGeneretor(SampleData.educations)}
      </div>
      <div className="flex flex-row justify-between my-1">
        <h5 className="font-bold">Skills</h5>
        {valueGeneretor(SampleData.skills)}
      </div>
    </div>
  );
};

export default Background;

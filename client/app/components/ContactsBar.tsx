import Icon from '@/public/icons/Icon';
import React from 'react';

const developer = {
  githubUrl: 'https://github.com/Finns841594',
  linkedinUrl: 'https://www.linkedin.com/in/feng-yang-511361166/',
};

const ContactsBar = () => {
  return (
    <div className="flex gap-2">
      <a href={developer.githubUrl} target="_blank">
        <Icon icon="github" className="h-6 w-6 fill-black" />
      </a>
      <a
        href={'https://www.linkedin.com/in/' + developer.linkedinUrl}
        target="_blank"
      >
        <Icon icon="linkedin" className="h-6 w-6 fill-black" />
      </a>
    </div>
  );
};

export default ContactsBar;

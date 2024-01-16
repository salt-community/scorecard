import Icon from '@/public/icons/Icon';
import React from 'react';

const developer = {
  gitHubUrl: 'https://github.com/Finns841594',
  linkedinUrl: 'https://www.linkedin.com/in/feng-yang-511361166/',
};

const ContactsBar = () => {
  return (
    <div>
      <div className="flex gap-2">
        <a href={developer.gitHubUrl} target="_blank">
          <Icon icon="github" className="h-8 w-8 fill-black" />
        </a>
        <a href={developer.linkedinUrl} target="_blank">
          <Icon icon="linkedin" className="h-8 w-8 fill-black" />
        </a>
      </div>
    </div>
  );
};

export default ContactsBar;

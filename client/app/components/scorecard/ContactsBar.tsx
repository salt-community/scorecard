import Icon from '@/public/icons/Icon';
import React from 'react';

interface ContactsBarProps {
  githubUrl: string
  linkedinUrl: string
}

const developer = {
  githubUrl: 'https://github.com/Finns841594',
  linkedinUrl: 'https://www.linkedin.com/in/feng-yang-511361166/',
};

const ContactsBar = ({githubUrl, linkedinUrl}: ContactsBarProps) => {
  return (
    <div className="flex gap-2">
      <a href={githubUrl} target="_blank">
        <Icon icon="github" className="h-6 w-6 fill-black" />
      </a>
      <a href={linkedinUrl} target="_blank">
        <Icon icon="linkedin" className="h-6 w-6 fill-black" />
      </a>
    </div>
  );
};

export default ContactsBar;

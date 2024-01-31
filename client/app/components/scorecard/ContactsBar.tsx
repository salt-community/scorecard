import Icon from "@/public/icons/Icon";
import React from "react";

type ContactsBarProps = {
  githubUrl: string;
  linkedinUrl: string;
};

const ContactsBar = ({ githubUrl, linkedinUrl }: ContactsBarProps) => {
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

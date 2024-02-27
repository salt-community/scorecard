import Icon from "@/public/icons/Icon";
import React from "react";

type ContactsBarProps = {
  email: string;
  githubUrl: string;
};

//Todo alter the emails target
const ContactsBar = ({ email, githubUrl }: ContactsBarProps) => {
  return (
    <div className="flex gap-2">
      <a href={email} target="_blank"> 
        <Icon icon="mail" className="h-6 w-6 fill-black" />
      </a>
      {/* <a href={githubUrl} target="_blank">
        <Icon icon="github" className="h-6 w-6 fill-black" />
      </a> */}
    </div>
  );
};

export default ContactsBar;

import Icon from "@/public/icons/Icon";
import React from "react";

type ContactsBarProps = {
  email: string;
};

//TO DO: alter target to redirect to email account somehow

const ContactsBar = ({ email }: ContactsBarProps) => {
  return (
    <div className="flex gap-2">
      <a href={email} target="_blank"> 
        <Icon icon="mail" className="h-6 w-6 fill-black" />
      </a>
      
    </div>
  );
};

export default ContactsBar;

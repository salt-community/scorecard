"use client";
import React, { useState } from 'react'
import { Button } from "@material-tailwind/react";
import { Input, Select, SelectItem } from "@nextui-org/react";
import { httpGetAccountByEmail } from '@/app/api/request';
import { Account } from '@/app/types';
import { useRouter } from 'next/navigation';


const Page = () => {
  const [isNotFound, setIsNotFound] = useState<boolean | undefined>(false);
  const [isNotFoundMessage, setIsNotFoundMessage] = useState<String>();
  const [input, setInput] = useState({
    email: "",
    role: "core"    
  });
  const router = useRouter();

  const handleOnChange = (event: any) => {
    const { name, value } = event.target;
    setInput({
      ...input,
      [name]: value,
    });
  };


  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const respone:Account = await httpGetAccountByEmail(input.email);
    if(respone === null || respone.role !== 'core'){
      setIsNotFound(true);
      setIsNotFoundMessage('No core team account found with email: ' + input.email);
    }else{
        router.push("/dashboard")
    }
    console.log(respone);
  }


  return (
    <div>
      <form onSubmit={(event) => handleSubmit(event)}>
        <Input 
        name='email' 
        type='email' 
        onChange={handleOnChange} 
        placeholder='Email:'
        isInvalid={isNotFound}
        errorMessage={isNotFoundMessage}
        ></Input>
        <Button
            className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
            placeholder={undefined}
            type="submit"
          >
            Login
        </Button>
      </form>
    </div>
  )
}

export default Page
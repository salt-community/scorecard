"use client";
import React, { useState } from 'react'
import { Button } from "@material-tailwind/react";
import { Input, Select, SelectItem } from "@nextui-org/react";
import { httpGetAccountByEmail } from '@/app/api/request';
import { Account } from '@/app/types';

const page = () => {
  const [isNotFound, setIsNotFound] = useState<Boolean>();
  const [isNotFoundMessage, setIsNotFoundMessage] = useState<String>();
  const [input, setInput] = useState({
    email: "",
    role: "core"    
  });

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
      setTimeout(() => {
        setIsNotFoundMessage('');
        setIsNotFound(false);
      }, 1000);
    }
    console.log(respone);
  }


  return (
    <div>
      {isNotFound && <div className='bg-red-500'>{isNotFoundMessage}</div>}
      <form onSubmit={(event) => handleSubmit(event)}>
        <Input name='email' type='email' onChange={handleOnChange} placeholder='Email:'></Input>
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

export default page
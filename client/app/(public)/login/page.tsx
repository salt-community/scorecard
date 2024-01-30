"use client";
import React, { useState } from 'react'
import { Button } from "@material-tailwind/react";
import { Input, Select, SelectItem } from "@nextui-org/react";

const page = () => {
  const [input, setInput] = useState({
    email: "",
    name: "",
    role: "core"    
  });

  const handleOnChange = (event: any) => {
    const { name, value } = event.target;
    setInput({
      ...input,
      [name]: value,
    });
  };


  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
  }

  console.log(input);
  return (
    <div>
      <form onSubmit={(event) => handleSubmit(event)}>
        <Input name='email' type='email' onChange={handleOnChange} placeholder='Email:'></Input>
        <Input name='name' type='text' onChange={handleOnChange} placeholder='Name:'></Input>
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
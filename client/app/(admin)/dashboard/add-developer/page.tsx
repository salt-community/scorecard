'use client';
import React, { useState } from 'react'
import { Button } from "@material-tailwind/react";
import { Input, Select, SelectItem } from '@nextui-org/react';
import { httpPostDeveloper, httpPostScoreById } from '@/app/api/request';
const Page = () => {
    const [input, setInput] = useState({
        email: "",
        role: ""
      });
    
      const inputForm = (el: any) => {
        const { name, value } = el.target;
        setInput({
          ...input,
          [name]: value,
        });
      };
    
      const submitHandler = async (e: any, input: any) => {
        e.preventDefault();
    
        const response = await httpPostDeveloper(input);
        console.log(response);
        if (response.status == 500) {
          alert("Score already exist");
        } else {
          e.target.reset();
        }
      };
      const roles = ['core', 'saltie', 'pgp', 'consultant'];
  return (
    <div>      
    <form
    id="form"
    onSubmit={(e) => submitHandler(e, input)}
    className="flex flex-col h-full justify-start gap-4"
  >
    <div className=" w-full p-4 flex flex-col gap-4">

        <Input
          name="email"
          type="email"
          label="Email:"
          labelPlacement="outside"
          placeholder="Enter email"
          color="default"
          step={"1"}
          onChange={inputForm}
          className="w-72"
        />
                <Input
          name="name"
          type="text"
          label="Name:"
          labelPlacement="outside"
          placeholder="Enter Name"
          color="default"
          step={"1"}
          onChange={inputForm}
          className="w-72"
        />
                <Input
          name="phone-number"
          type="text"
          label="Phone Number:"
          labelPlacement="outside"
          placeholder="Enter Phone Number"
          color="default"
          step={"1"}
          onChange={inputForm}
          className="w-72"
        />
                <Select
          name="role"
          label="Role :"
          labelPlacement="outside"
          placeholder="Please choose role..."
          onChange={inputForm}
        >
          {roles.map((roles: string) => (
            <SelectItem key={roles} value={roles}>
              {roles}
            </SelectItem>
          ))}
        </Select>
     
    </div>
    <div className="flex flex-row justify-center">
      <Button
        className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
        placeholder={undefined}
        type="submit"
      >
        Submit
      </Button>
    </div>
  </form></div>
  )
}

export default Page
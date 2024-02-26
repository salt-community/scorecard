"use client";
import { AddDeveloperForm } from "@/app/components/AddDeveloperForm";
import { ListAllDevelopers } from "@/app/components/ListAllDevelopers";
import { Developer, postDeveloper } from "@/server";
import React, { useState } from "react";

export default function DeveloperPage({ params }: { params: { id: string } }) {
  const [developers, setDevelopers] = useState<Developer[]>([]);
  
  return (
    <>
      <AddDeveloperForm setDevelopers={setDevelopers} postDeveloper={postDeveloper} accountId={params.id} />
      <ListAllDevelopers setDevelopers={setDevelopers} developers={developers}/>
    </>
  );
}

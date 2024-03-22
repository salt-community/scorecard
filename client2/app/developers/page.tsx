"use client";
import { ListAllDevelopers } from "@/app/components/ListAllDevelopers";
import { Developer } from "@/server";
import { Button, Link } from "@nextui-org/react";
import React, { useEffect, useState } from "react";

export default function DeveloperPage({ params }: { params: { id: string } }) {
  const [developers, setDevelopers] = useState<Developer[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/v2/developers`,
          {
            cache: "no-cache",
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to fetch data");
        }

        const data = await response.json();
        const listOfDevelopers = data.developerResponseList as Developer[];
        setDevelopers(listOfDevelopers);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);
  
  return (
    <>
      <ListAllDevelopers developers={developers}/>
      <div style={{ position: 'absolute', bottom: '20px', right: '20px' }}>
        <Link href="/developers/add"><Button>Add a Developer</Button></Link>
      </div>
    </>
  );
}

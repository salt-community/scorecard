"use client";
import { httpGetDeveloperById } from "@/app/api/request";
import ScoreCard from "../../../components/scorecard/ScoreCard";
import { DeveloperData } from "@/app/types";

export default async function Page({ params }: { params: { slug: string } }) {
  const BASIC_URI = process.env.NEXT_PUBLIC_API_URL;

  const httpGetDeveloperById = async (id: string) => {
    const response = await fetch(`${BASIC_URI}/api/developers/${id}`, {
      cache: "no-cache",
    });
    return await response.json();
  };
  // const developerData = await httpGetDeveloperById(params.slug);
  const developerData: DeveloperData = {
    id: "4389f66b-326a-4400-b0a1-a5dcd6a9b882",
    email: "feng.yang@appliedtechnology.se",
    name: "feng yang",
    standoutIntro: "experienced in various of client projects",
    bootcamp: "javascript",
    githubUrl: "https://github.com/Finns841594",
    githubUserName: "Finns841594",
    githubProfilePictureUrl: "https://github.com/Finns841594.png",
    linkedinUrl: "https://www.linkedin.com/in/feng-yang-511361166",
    codewarsUrl: "https://www.codewars.com/users/Finns841594",
    radarGraph: [
      {
        subject: "frontend",
        score: 0,
        fullMark: 100,
      },
      {
        subject: "backend",
        score: 0,
        fullMark: 100,
      },
      {
        subject: "charismatic",
        score: 0,
        fullMark: 100,
      },
      {
        subject: "teamwork",
        score: 0,
        fullMark: 100,
      },
      {
        subject: "design",
        score: 0,
        fullMark: 100,
      },
      {
        subject: "management",
        score: 0,
        fullMark: 100,
      },
    ],
    scores: [],
    selectedProjects: [
      {
        name: "moboga",
        repoUrl: "https://github.com/Finns841594/moboga",
        data: {
          commits: 0,
          issues: 0,
          duration: 0,
          performance: 0,
          testCoverages: 0,
        },
      },
      {
        name: "jobMatches",
        repoUrl: "https://github.com/lups-tech/jobMatches",
        data: {
          commits: 0,
          issues: 0,
          duration: 0,
          performance: 0,
          testCoverages: 0,
        },
      },
    ],
    backgroundInformation: {
      nationalities: ["chinese"],
      spokenLanguages: {
        swedish: "intermediate",
        chinese: "natives",
        english: "fluent",
      },
      academic: {
        id: "9a081b77-3d41-4ab3-9d31-5099e7c7ee42",
        degree: "master",
        major: "architecture",
        startDate: "2018-08-12",
        endDate: "2020-08-12",
        school: "KTH Royal Institute of Technology",
      },
      skills: [
        "javascript",
        "typescript",
        "react",
        "next.js",
        "node.js",
        "express",
        "mongodb",
      ],
    },
    averages: [
      {
        scoreName: "communication",
        average: 0,
      },
      {
        scoreName: "planning",
        average: 0,
      },
      {
        scoreName: "coding",
        average: 0,
      },
      {
        scoreName: "total",
        average: 0,
      },
    ],
  };

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-12 md:p-24">
      <div>
        <ScoreCard developerData={developerData} />
      </div>
    </main>
  );
}

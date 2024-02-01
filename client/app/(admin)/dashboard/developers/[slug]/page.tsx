"use client";
import {
  httpGetAdmninDeveloperById,
  httpGetDeveloperById,
} from "@/app/api/request";
import SelectNationalities from "@/app/components/admin/SelectNationalities";
import SelectLanguages from "@/app/components/admin/SelectLanguages";
import SelectProjects from "@/app/components/admin/SelectProjects";
import SelectSkills from "@/app/components/admin/SelectSkills";
import { developerDetail } from "@/app/types";
import {
  Avatar,
  Card,
  CardHeader,
  Input,
  Select,
  SelectItem,
} from "@nextui-org/react";
import { useEffect, useState } from "react";
import { Button } from "@material-tailwind/react";

export default function DeveloperDetailPage({
  params,
}: {
  params: { slug: string };
}) {
  const roles = ["core", "saltie", "pgp", "consultant"];
  const bootcamps = ["java", "javascript", "dotnet"];
  const [developer, setDeveloper] = useState<developerDetail>();
  const [input, setInput] = useState(developer!);
  const BASIC_URI = process.env.NEXT_PUBLIC_API_URL;

  const httpGetAdmninDeveloperById = async (id: string) => {
    const response = await fetch(
      `${BASIC_URI}/api/developers/admin/developer/${id}`,
      {
        cache: "no-cache",
      }
    );
    return await response.json();
  };

  const fetchbackend = async () => {
    // const developerData = await httpGetAdmninDeveloperById(params.slug);
    const developerData = {
      account: {
        id: "4389f66b-326a-4400-b0a1-a5dcd6a9b882",
        email: "feng.yang@appliedtechnology.se",
        role: "pgp",
      },
      userDetail: {
        id: "5d475cdf-399f-4ba6-8ea1-4eca63e21ad7",
        name: "feng yang",
        introduction: "experienced in various of client projects",
        phoneNumber: "071234456",
        bootcamp: "javascript",
      },
      academic: {
        id: "9a081b77-3d41-4ab3-9d31-5099e7c7ee42",
        degree: "master",
        major: "architecture",
        startDate: "2018-08-12",
        endDate: "2020-08-12",
        school: "KTH Royal Institute of Technology",
      },
      social: {
        id: "f75b2e41-49b6-4f1c-93a1-ae145cda5b07",
        linkedInUrl: "https://www.linkedin.com/in/feng-yang-511361166",
        codewarsUrl: "https://www.codewars.com/users/Finns841594",
      },
      github: {
        id: "1bda28d3-3863-49ba-8e26-59ff219263e8",
        url: "https://github.com/Finns841594",
        pictureUrl: "https://github.com/Finns841594.png",
      },
      projects: [
        {
          id: "50e8141f-f8dd-430c-a0b9-6b0f33a3054a",
          url: "https://github.com/Finns841594/moboga",
          commit: 0,
          issue: 0,
          duration: 0,
          performance: 0,
          testCoverage: 0,
        },
        {
          id: "a909f9cd-f6d7-403c-81a0-330e0982705f",
          url: "https://github.com/lups-tech/jobMatches",
          commit: 0,
          issue: 0,
          duration: 0,
          performance: 0,
          testCoverage: 0,
        },
      ],
      skills: [
        {
          id: "315d221d-921d-4e84-9169-ef9b79c963d8",
          skill: "javascript",
        },
        {
          id: "6a207665-7e00-4bae-9fce-69341b652074",
          skill: "typescript",
        },
        {
          id: "c329f36c-913c-4ff6-90ba-33fd71105adf",
          skill: "react",
        },
        {
          id: "0bdf99a6-a12a-4408-b5fd-83dfbb399a1a",
          skill: "next.js",
        },
        {
          id: "651ba34a-d5ea-4359-a962-17d20b711c7f",
          skill: "node.js",
        },
        {
          id: "f708d672-f0d6-4b49-a944-f552e06ae0ee",
          skill: "express",
        },
        {
          id: "199c20c1-d37f-49fa-b88a-eea3a2c7d90f",
          skill: "mongodb",
        },
      ],
      languages: [
        {
          id: "9d06f70b-638a-4b2e-ab74-febb4cab5d00",
          language: "chinese",
          fluency: "natives",
        },
        {
          id: "25c65a4b-b16e-4b81-91b7-ced751310702",
          language: "english",
          fluency: "fluent",
        },
        {
          id: "d377bd2e-0ccb-4321-a406-144a3c5ce100",
          language: "swedish",
          fluency: "intermediate",
        },
      ],
      nationalities: [
        {
          id: "68e7c37e-d6d8-49c0-96ca-8a5c7ae136bb",
          nationality: "chinese",
        },
      ],
    };
    setDeveloper(developerData);
  };

  const inputForm = (el: any) => {
    const { name, value } = el.target;
    setInput({
      ...input,
      [name]: value,
    });
  };
  const handleSubmit = () => {};

  useEffect(() => {
    fetchbackend();
  }, []);

  if (!developer) {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }

  return (
    <form action="">
      <Card className="flex flex-row gap-4">
        <div className={` flex-1 py-4 gap-3`}>
          <CardHeader className="pb-0">
            <h4 className="font-bold text-large">Profile</h4>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2 w-full py-0">
            <Avatar
              isBordered
              color="default"
              src={developer.github.pictureUrl}
              className=" w-24 h-24 text-large "
              name={developer.userDetail.name}
            />
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Name :</label>
              <Input
                name="name"
                type="text"
                placeholder="Enter name"
                color="default"
                labelPlacement="outside-left"
                onChange={inputForm}
                className="w-2/3 py-0"
                defaultValue={developer.userDetail.name}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Class :</label>
              <Select
                name="bootcamp"
                labelPlacement="outside-left"
                placeholder={
                  developer.userDetail.bootcamp
                    ? developer.userDetail.bootcamp
                    : "Please choose bootcamp..."
                }
                onChange={inputForm}
                className="w-2/3 py-0"
                value={developer.userDetail.bootcamp}
              >
                {bootcamps.map((bootcamp, index) => (
                  <SelectItem key={index} value={bootcamp}>
                    {bootcamp}
                  </SelectItem>
                ))}
              </Select>
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Role :</label>
              <Select
                name="role"
                labelPlacement="outside-left"
                placeholder={
                  developer.account.role
                    ? developer.account.role
                    : "Please choose bootcamp..."
                }
                onChange={inputForm}
                className="w-2/3 py-0"
                value={developer.account.role}
              >
                {roles.map((roles, index) => (
                  <SelectItem key={index} value={roles}>
                    {roles}
                  </SelectItem>
                ))}
              </Select>
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Email :</label>
              <Input
                name="email"
                type="email"
                labelPlacement="outside-left"
                placeholder="Enter email"
                color="default"
                onChange={inputForm}
                className="w-2/3 py-0"
                defaultValue={developer.account.email}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Phone :</label>
              <Input
                name="phoneNumber"
                type="text"
                labelPlacement="outside-left"
                placeholder="Enter phoneNumber"
                color="default"
                onChange={inputForm}
                className="w-2/3 py-0"
                defaultValue={developer.userDetail.phoneNumber}
              />
            </div>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <SelectNationalities nationalitiesSet={developer.nationalities} />
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <SelectLanguages languagesSet={developer.languages} />
          </CardHeader>
        </div>
        <div className={` flex-1 py-4`}>
          <CardHeader>
            <h4 className="font-bold text-large">Academic</h4>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Degree :</label>
              <Input
                name="degree"
                type="text"
                labelPlacement="outside-left"
                placeholder="Enter degree"
                color="default"
                onChange={inputForm}
                className="w-72"
                defaultValue={developer.academic.degree}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Major :</label>
              <Input
                name="major"
                type="text"
                labelPlacement="outside-left"
                placeholder="Enter major"
                color="default"
                onChange={inputForm}
                className="w-72"
                defaultValue={developer.academic.major}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">School :</label>
              <Input
                name="school"
                type="text"
                labelPlacement="outside-left"
                placeholder="Enter school"
                color="default"
                onChange={inputForm}
                className="w-72"
                defaultValue={developer.academic.school}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Start :</label>
              <Input
                name="startDate"
                type="date"
                labelPlacement="outside-left"
                placeholder="Enter date"
                color="default"
                onChange={inputForm}
                className="w-72"
                defaultValue={developer.academic.startDate}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">End :</label>
              <Input
                name="endDate"
                type="date"
                labelPlacement="outside-left"
                placeholder="Enter date"
                color="default"
                onChange={inputForm}
                className="w-72"
                defaultValue={developer.academic.endDate}
              />
            </div>
          </CardHeader>

          <CardHeader className=" px-4 flex flex-col gap-2">
            <SelectSkills skillsSet={developer.skills} />
          </CardHeader>
        </div>
        <div className={` flex-1 py-4`}>
          <CardHeader>
            <h4 className="font-bold text-large">Social</h4>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Github :</label>
              <Input
                name="githubUsername"
                type="text"
                labelPlacement="outside-left"
                placeholder="github username"
                color="default"
                onChange={inputForm}
                className="w-72"
                defaultValue={developer.github.url.split("/").pop()}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Codewars :</label>
              <Input
                name="codewarsUrl"
                type="text"
                labelPlacement="outside-left"
                placeholder="codewars username"
                color="default"
                onChange={inputForm}
                className="w-72"
                defaultValue={developer.social.codewarsUrl.split("/").pop()}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Linked In :</label>
              <Input
                name="LinkedinUsername"
                type="text"
                labelPlacement="outside-left"
                placeholder="linkedIn username"
                color="default"
                onChange={inputForm}
                className="w-72"
                defaultValue={developer.social.linkedInUrl.split("/").pop()}
              />
            </div>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <SelectProjects projectsSet={developer.projects} />
          </CardHeader>
          <Button
            className="bg-blue-400 absolute bottom-5 right-5  hover:bg-accent text-white font-bold py-2 px-4 rounded w-24"
            placeholder={undefined}
            type="submit"
          >
            Save
          </Button>
        </div>
      </Card>
    </form>
  );
}

"use client";
import {
  httpGetAdmninDeveloperById,
  httpGetDeveloperById,
  httpUpdateDeveloperById,
} from "@/app/api/request";
import SelectNationalities from "@/app/components/admin/SelectNationalities";
import SelectLanguages from "@/app/components/admin/SelectLanguages";
import SelectProjects from "@/app/components/admin/SelectProjects";
import SelectSkills from "@/app/components/admin/SelectSkills";
import {
  developerDetail,
  language,
  nationality,
  project,
  skill,
} from "@/app/types";
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
  const [developer, setDeveloper] = useState<developerDetail>({
    account: {
      id: "",
      email: "",
      role: "",
    },
    userDetail: {
      id: "",
      name: "",
      introduction: "",
      phoneNumber: "",
      bootcamp: "",
    },
    academic: {
      id: "",
      degree: "",
      major: "",
      startDate: "",
      endDate: "",
      school: "",
    },
    social: {
      id: "",
      linkedInUrl: "",
      codewarsUrl: "",
    },
    github: {
      id: "",
      url: "",
      pictureUrl: "",
    },
    projects: [],
    skills: [],
    languages: [],
    nationalities: [],
  });

  const fetchbackend = async () => {
    const developerData = await httpGetAdmninDeveloperById(params.slug);
    setDeveloper(developerData);
  };

  function handleUserDetailChange(e: any) {
    const { name, value } = e.target;
    setDeveloper({
      ...developer,
      userDetail: {
        ...developer?.userDetail,
        [name]: value,
      },
    });
  }

  function handleAccountChange(e: any) {
    const { name, value } = e.target;
    setDeveloper({
      ...developer,
      account: {
        ...developer?.account,
        [name]: value,
      },
    });
  }

  function handleAcademicChange(e: any) {
    const { name, value } = e.target;
    setDeveloper({
      ...developer,
      academic: {
        ...developer?.academic,
        [name]: value,
      },
    });
  }

  function handleSocialChange(e: any) {
    const { name, value } = e.target;
    if (name === "") console.log(name + value);
    setDeveloper({
      ...developer,
      social: {
        ...developer?.social,
        [name]: value,
      },
    });
  }

  function handleGithubChange(e: any) {
    const { name, value } = e.target;
    if (name === "") console.log(name + value);
    setDeveloper({
      ...developer,
      github: {
        ...developer?.github,
        [name]: value,
      },
    });
  }

  function handleNationalityChange(updatedNationalities: nationality[]) {
    setDeveloper({
      ...developer,
      nationalities: updatedNationalities,
    });
  }

  function handleLanguagesChange(updatedLanguages: language[]) {
    setDeveloper({
      ...developer,
      languages: updatedLanguages,
    });
  }

  function handleSkillsChange(updatedSkills: skill[]) {
    setDeveloper({
      ...developer,
      skills: updatedSkills,
    });
  }

  function handleProjectsChange(updatedProjects: project[]) {
    console.log(updatedProjects);
    setDeveloper({
      ...developer,
      projects: updatedProjects,
    });
  }

  const updateData = (el: any) => {
    const { name, value } = el.target;
    const nameSplit = name.split(".")[1];
    const level: string = name.split(".")[0];
    console.log(el);
    developer!.userDetail.name = value;
  };

  useEffect(() => {
    fetchbackend();
  }, []);

  if (developer.account.id === "") {
    return (
      <div>
        <h1>Loading ...</h1>
      </div>
    );
  }
  const handleSubmit = async (event: any, input: developerDetail) => {
    console.log(input);
    event.preventDefault();
    const req = await httpUpdateDeveloperById(params.slug, input);
  };
  return (
    <form onSubmit={(e) => handleSubmit(e, developer)}>
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
                onChange={handleUserDetailChange}
                className="w-2/3 py-0"
                defaultValue={developer.userDetail.name}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Class :</label>
              <select
                name="bootcamp"
                //labelPlacement="outside-left"
                /*                 placeholder={
                  developer.userDetail.bootcamp
                    ? developer.userDetail.bootcamp
                    : "Please choose bootcamp..."
                } */
                onChange={handleUserDetailChange}
                className="w-2/3 py-0"
                defaultValue={developer.userDetail.bootcamp}
              >
                {bootcamps.map((bootcamp, index) => (
                  <option key={index} value={bootcamp}>
                    {bootcamp}
                  </option>
                ))}
              </select>
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Role :</label>
              <select
                name="role"
                //labelPlacement="outside-left"
                /*      placeholder={
                  developer.account.role
                    ? developer.account.role
                    : "Please choose bootcamp..."
                } */
                onChange={handleAccountChange}
                className="w-2/3 py-0"
                defaultValue={developer.account.role}
              >
                {roles.map((roles, index) => (
                  <option key={index} value={roles}>
                    {roles}
                  </option>
                ))}
              </select>
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Email :</label>
              <Input
                name="email"
                type="email"
                labelPlacement="outside-left"
                placeholder="Enter email"
                color="default"
                onChange={handleAccountChange}
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
                onChange={handleUserDetailChange}
                className="w-2/3 py-0"
                defaultValue={developer.userDetail.phoneNumber}
              />
            </div>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <SelectNationalities
              nationalitiesSet={developer.nationalities}
              onNationalityChange={handleNationalityChange}
            />
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <SelectLanguages
              languagesSet={developer.languages}
              onLanguageChange={handleLanguagesChange}
            />
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
                onChange={handleAcademicChange}
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
                onChange={handleAcademicChange}
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
                onChange={handleAcademicChange}
                className="w-72"
                defaultValue={developer.academic.school}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Start date :</label>
              <Input
                name="startDate"
                type="date"
                labelPlacement="outside-left"
                placeholder="Enter date"
                color="default"
                onChange={handleAcademicChange}
                className="w-72"
                defaultValue={developer.academic.startDate}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">End date :</label>
              <Input
                name="endDate"
                type="date"
                labelPlacement="outside-left"
                placeholder="Enter date"
                color="default"
                onChange={handleAcademicChange}
                className="w-72"
                defaultValue={developer.academic.endDate}
              />
            </div>
          </CardHeader>

          <CardHeader className=" px-4 flex flex-col gap-2">
            <SelectSkills
              skillsSet={developer.skills}
              onSkillsChange={handleSkillsChange}
            />
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
                name="url"
                type="text"
                labelPlacement="outside-left"
                placeholder="github username"
                color="default"
                onChange={handleGithubChange}
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
                onChange={handleSocialChange}
                className="w-72"
                defaultValue={developer.social.codewarsUrl.split("/").pop()}
              />
            </div>
            <div className="w-full flex flex-row items-center">
              <label className="w-1/3">Linked In :</label>
              <Input
                name="linkedInUrl"
                type="text"
                labelPlacement="outside-left"
                placeholder="linkedIn username"
                color="default"
                onChange={handleSocialChange}
                className="w-72"
                defaultValue={developer.social.linkedInUrl.split("/").pop()}
              />
            </div>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <SelectProjects
              projectsSet={developer.projects}
              onProjectsChange={handleProjectsChange}
            />
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

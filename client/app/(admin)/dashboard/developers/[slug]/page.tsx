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

export default function DeveloperDetailPage({
  params,
}: {
  params: { slug: string };
}) {
  const roles = ["core", "saltie", "pgp", "consultant"];
  const bootcamps = ["java", "javascript", "dotnet"];
  const [developer, setDeveloper] = useState<developerDetail>();
  const [input, setInput] = useState(developer!);

  const fetchbackend = async () => {
    const developerData = await httpGetAdmninDeveloperById(params.slug);
    setDeveloper(developerData);
    console.log(developerData);
  };

  const inputForm = (el: any) => {
    const { name, value } = el.target;
    setInput({
      ...input,
      [name]: value,
    });
  };

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
      <div className="flex flex-row gap-4">
        <Card className={` flex-1`}>
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
            <SelectNationalities nationalitiesSet={developer.nationalities} />
            <SelectLanguages languagesSet={developer.languages} />
          </CardHeader>
        </Card>
        <Card className={` flex-1`}>
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
        </Card>
        <Card className={` flex-1`}>
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
            <SelectProjects projectsSet={developer.projects} />
          </CardHeader>
        </Card>
      </div>
    </form>
  );
}

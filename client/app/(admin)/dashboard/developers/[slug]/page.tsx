"use client";
import { httpGetDeveloperById } from "@/app/api/request";
import SelectNationalities from "@/app/components/SelectNationalities";
import SelectLanguages from "@/app/components/admin/SelectLanguages";
import SelectProjects from "@/app/components/admin/SelectProjects";
import SelectSkills from "@/app/components/admin/SelectSkills";
import { DeveloperData } from "@/app/types";
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
  const [developer, setDeveloper] = useState<DeveloperData>();
  const [input, setInput] = useState(developer!);

  const fetchbackend = async () => {
    const developerData = await httpGetDeveloperById(params.slug);
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
          <CardHeader>
            <h4 className="font-bold text-large">Profile</h4>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <Avatar
              isBordered
              color="default"
              src={developer.githubProfilePictureUrl}
              className=" w-24 h-24 text-large "
              name={developer.name}
            />
            <Input
              name="name"
              type="text"
              label="Name :"
              labelPlacement="outside-left"
              placeholder="Enter name"
              color="default"
              step={"1"}
              onChange={inputForm}
              className="w-72"
              defaultValue={developer.name}
            />
            <Select
              name="bootcamp"
              label="Bootcamp :"
              labelPlacement="outside-left"
              placeholder="Please choose bootcamp..."
              onChange={inputForm}
              defaultSelectedKeys={developer.bootcamp}
            >
              {bootcamps.map((bootcamp: string) => (
                <SelectItem key={bootcamp} value={bootcamp}>
                  {bootcamp}
                </SelectItem>
              ))}
            </Select>
            <Select
              name="role"
              label="Role :"
              labelPlacement="outside-left"
              placeholder="Please choose role..."
              onChange={inputForm}
            >
              {roles.map((roles: string) => (
                <SelectItem key={roles} value={roles}>
                  {roles}
                </SelectItem>
              ))}
            </Select>
            <Input
              name="email"
              type="email"
              label="Email :"
              labelPlacement="outside-left"
              placeholder="Enter email"
              color="default"
              step={"1"}
              onChange={inputForm}
              className="w-72"
              defaultValue={developer.email}
            />
            <Input
              name="phoneNumber"
              type="text"
              label="Phone :"
              labelPlacement="outside-left"
              placeholder="Enter phoneNumber"
              color="default"
              onChange={inputForm}
              className="w-72"
            />
            <SelectNationalities />
            <SelectLanguages />
          </CardHeader>
        </Card>
        <Card className={`max-w-72 flex-1`}>
          <CardHeader>
            <h4 className="font-bold text-large">Academic</h4>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <Input
              name="degree"
              type="text"
              label="Degree :"
              labelPlacement="outside-left"
              placeholder="Enter degree"
              color="default"
              onChange={inputForm}
              className="w-72"
              defaultValue={developer.backgroundInformation.academic.degree}
            />
            <Input
              name="major"
              type="text"
              label="Major :"
              labelPlacement="outside-left"
              placeholder="Enter major"
              color="default"
              onChange={inputForm}
              className="w-72"
              defaultValue={developer.backgroundInformation.academic.major}
            />
            <Input
              name="school"
              type="text"
              label="School :"
              labelPlacement="outside-left"
              placeholder="Enter school"
              color="default"
              onChange={inputForm}
              className="w-72"
              defaultValue={developer.backgroundInformation.academic.school}
            />
            <Input
              name="startDate"
              type="date"
              label="Start Date :"
              labelPlacement="outside-left"
              placeholder="Enter date"
              color="default"
              onChange={inputForm}
              className="w-72"
              defaultValue={developer.backgroundInformation.academic.startDate}
            />
            <Input
              name="endDate"
              type="date"
              label="End Date :"
              labelPlacement="outside-left"
              placeholder="Enter date"
              color="default"
              onChange={inputForm}
              className="w-72"
              defaultValue={developer.backgroundInformation.academic.endDate}
            />
          </CardHeader>

          <CardHeader className=" px-4 flex flex-col gap-2">
            <SelectSkills />
          </CardHeader>
        </Card>
        <Card className={` flex-1`}>
          <CardHeader>
            <h4 className="font-bold text-large">Social</h4>
          </CardHeader>
          <CardHeader className=" px-4 flex flex-col gap-2">
            <Input
              name="githubUsername"
              type="text"
              label="Github :"
              labelPlacement="outside-left"
              placeholder="github username"
              color="default"
              onChange={inputForm}
              className="w-72"
              defaultValue={developer.githubUserName}
            />
            <Input
              name="codewarsUrl"
              type="text"
              label="Codewars :"
              labelPlacement="outside-left"
              placeholder="codewars username"
              color="default"
              onChange={inputForm}
              className="w-72"
            />
            <Input
              name="LinkedinUsername"
              type="text"
              label="LinkedIn :"
              labelPlacement="outside-left"
              placeholder="linkedIn username"
              color="default"
              onChange={inputForm}
              className="w-72"
            />
          </CardHeader>
          <SelectProjects />
        </Card>
      </div>
    </form>
  );
}

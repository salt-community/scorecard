"use client";
import { Account } from "@/server/developer/route";
import {
  Button,
  Card,
  CardBody,
  Input,
  Select,
  SelectItem,
} from "@nextui-org/react";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function AddDeveloper() {
  const [accounts, setAccounts] = useState<Account[]>([]);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [bootcamp, setBootcamp] = useState("JAVA");
  const [githubUsername, setGithubUsername] = useState("");
  const [accountId, setaccountId] = useState("");
  const router = useRouter();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/v2/accounts/non-dev`,
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
        const listOfAccounts = data.accountResponseList as Account[];
        setAccounts(listOfAccounts);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);

  const handleAddDeveloper = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    await fetch(`http://localhost:8080/api/v2/developers`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        firstName,
        lastName,
        emailAddress: accounts.find((a) => a.id === accountId)?.emailAddress,
        bootcamp,
        githubUsername,
        accountId,
      }),
    });
    router.push("/developers");
  };    

return (
  <>
        <p className="text-center p-8 font-bold">Add a Developer</p>
    <div className="flex justify-center p-2"> 
      <div className="flex flex-col">
        <Card className="max-w-full w-[340px] h-[400px]">
          <CardBody className="overflow-hidden">
            <form
              className="flex flex-col gap-4 h-[300px]"
              onSubmit={handleAddDeveloper}
            >
              <select
                required
                value={accountId}
                onChange={(e) => setaccountId(e.target.value)}
              >
                <option value={""}>Select an account </option>
                {accounts.map((account) => (
                  <option key={account.id} value={account.id}>
                    {account.emailAddress}
                  </option>
                ))}
              </select>
              <Input
                isRequired
                label="First Name"
                placeholder="Enter first name"
                type="text"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
              />
              <Input
                isRequired
                label="Last Name"
                placeholder="Enter first name"
                type="text"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
              />
              <Select
                id="bootcampCourse"
                name="bootcampCourse"
                labelPlacement="outside-left"
                placeholder="Please choose a bootcamp..."
                onChange={(e) => setBootcamp(e.target.value)}
              >
                <SelectItem key="Javascript" value={"Javascript"}>
                  Javascript
                </SelectItem>
                <SelectItem key="Java" value={"Java"}>
                  Java
                </SelectItem>
                <SelectItem key=".Net" value={".Net"}>
                  .Net
                </SelectItem>
              </Select>
              <Input
                isRequired
                label="GitHub"
                placeholder="Enter GitHub username"
                type="text"
                value={githubUsername}
                onChange={(e) => setGithubUsername(e.target.value)}
              />
              <div className="flex gap-2 justify-end">
                <Button fullWidth color="primary" type="submit">
                  Submit
                </Button>
              </div>
            </form>
          </CardBody>
        </Card>
      </div>
    </div>
  </>
);
                }
import axios from "axios";

export type PostDeveloperFunction = ({
  developer,
}: {
  developer: DeveloperFormInfo;
}) => Promise<void>;

export type DeveloperFormInfo = {
  firstName: string;
  lastName: string;
  emailAddress: string;
  bootcampCourse: string;
  githubUsername: string;
  accountId: string;
};

export type GetADeveloperFunction = ({
  developerId,
}: {
  developerId: string;
}) => Promise<Developer>;

export type Developer = {
  developerId: string;
  firstName: string;
  lastName: string;
  emailAddress: string;
  bootcampCourse: string;
  averageBackendScore: number;
  averageFrontendScore: number;
  githubImage: string;
};

export const postDeveloper: PostDeveloperFunction = async ({ developer }) => {
  try {
    const payload = {
      ...developer,
    };
    await axios.post("http://localhost:8080/api/v2/developers", payload, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  } catch (error) {
    throw new Error(`Error submiting developer:${String(error)}`);
  }
};

export const getADeveloper: GetADeveloperFunction = async ({ developerId }) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v2/developers/${developerId}`,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    const developerData = response.data as Developer;
    return developerData;
  } catch (error) {
    throw new Error(`Error getting the developer:${String(error)}`);
  }
};

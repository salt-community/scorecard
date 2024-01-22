export type SimpleTableEntry = { [key: string]: string | number };

export type BackgroundInformation = {
  nationalities: string[];
  spokenLanguages: string[];
  educations: string[];
  skills: string[];
};

export type DeveloperData = {
  developerId: string;
  name: string;
  standoutIntro: string;
  mainProgrammingLanguage: string;
  githubUrl: string;
  linkedinUrl: string;
  backgroundInformations: BackgroundInformation;
  githubUserName: string;
  githubProfilePictureUrl: string;
  selectedProjects: string[];
  commitsCount: number;
  issuesCount: number;
};

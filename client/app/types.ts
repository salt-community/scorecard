export type SimpleTableEntry = { [key: string]: string | number };

export type BackgroundInformation = {
  nationalities: string[];
  spokenLanguages: string[];
  educations: string[];
  skills: string[];
};

export type Scores = { scoreName: string; data: SimpleTableEntry };

export type Project = { name: string; repoUrl: string; data: SimpleTableEntry };

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
  scores: Scores[];
  selectedProjects: Project[];
  commitsCount?: number;
  issuesCount?: number;
};

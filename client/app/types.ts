//final type
export type DeveloperData = {
  id: string;
  email: string;
  name: string;
  standoutIntro: string;
  bootcamp: string;
  githubUrl: string;
  githubUserName: string;
  githubProfilePictureUrl: string;
  linkedinUrl: string;
  codewarsUrl: string;
  radarGraph: RadarGraphicData[];
  scores: Score[];
  selectedProjects: Project[];
  backgroundInformation: BackgroundInformation;
  averages: Average[];
};

export type SaltieData = {
  id: string;
  name: string;
  bootcamp: string;
  githubProfilePictureUrl: string;
  githubUrl: string;
  linkedinUrl: string;
  radarGraph: RadarGraphicData[];
  averages: Average[];
  scores: Score[];
};

export type BackgroundInformation = {
  nationalities: string[];
  spokenLanguages: SimpleTableEntry;
  academic: academic;
  skills: string[];
};

export type Project = { name: string; repoUrl: string; data: SimpleTableEntry };

export type RadarGraphicData = {
  subject: string;
  score: number;
  fullmark: number;
};

export type Score = {
  id: string;
  type: string;
  assignment: string;
  score: number;
  description: string;
};

export type DetailScores = {
  scoreName: string;
  average: number;
  data: Score[];
};

export type Average = {
  scoreName: string;
  average: number;
};

export type Assignment = {
  id: string;
  name: string;
  type: string;
};

export type SimpleTableEntry = { [key: string]: string | number };

export type developerDetail = {
  account: account;
  userDetail: userDetail;
  academic: academic;
  social: social;
  github: github;
  projects: project[];
  skills: skill[];
  languages: language[];
  nationalities: nationality[];
};

export type account = {
  id: string;
  email: string;
  role: string;
};

export type userDetail = {
  id: string;
  name: string;
  introduction: string;
  phoneNumber: string;
  bootcamp: string;
};

export type academic = {
  id: string;
  degree: string;
  major: string;
  startDate: string;
  endDate: string;
  school: string;
};

export type social = {
  id: string;
  linkedInUrl: string;
  codewarsUrl: string;
};

export type github = {
  id: string;
  url: string;
  pictureUrl: string;
};

export type language = {
  id: string;
  language: string;
  fluency: string;
};

export type project = {
  id: string;
  url: string;
  commit: string;
  issue: string;
  duration: string;
  performance: string;
  testCoverage: string;
};

export type skill = {
  id: string;
  skill: string;
};

export type nationality = {
  id: string;
  nationality: string;
};

export type Account = {
  id: string;
  email: string;
  name: string;
  role: string;
};

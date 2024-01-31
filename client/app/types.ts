export type SimpleTableEntry = { [key: string]: string | number };

export type academic = {
  id: string;
  degree: string;
  major: string;
  startDate: string;
  endDate: string;
  school: string;
};

export type BackgroundInformation = {
  nationalities: string[];
  spokenLanguages: SimpleTableEntry;
  academic: academic;
  skills: string[];
};

export type Assignment = {
  id: string;
  name: string;
  type: string;
};

export type Scores = { scoreName: string; data: ScoreRes[] };

export type Project = { name: string; repoUrl: string; data: SimpleTableEntry };

export type RadarGraphicData = {
  subject: string;
  score: number;
  fullMark: number;
};

export type DeveloperData = {
  id: string;
  email: string;
  name: string;
  standoutIntro: string;
  bootcamp: string;
  githubUrl: string;
  linkedinUrl: string;
  backgroundInformation: BackgroundInformation;
  githubUserName: string;
  githubProfilePictureUrl: string;
  scores: ScoreRes[];
  selectedProjects: Project[];
  radarGraph: RadarGraphicData[];
  commitsCount?: number;
  issuesCount?: number;
  averages: Average[];
};

export type Average = {
  scoreName: string;
  average: number;
};

export type DeveloperShowcase = {
  id: string;
  email: string;
  name: string;
  standoutIntro: string;
};

export type SaltieData = {
  id: string;
  name: string;
  bootcamp: string;
  githubProfilePictureUrl: string;
  githubUrl: string;
  linkedinUrl: string;
  radarGraph: RadarGraphicData[];
  scores: ScoreRes[];
};

export type ScoreRes = {
  id: string;
  type: string;
  assignment: string;
  score: number;
  description: string;
};

export type DetailScores = { scoreName: string; data: Score[] };

export type Score = {
  id: string;
  assignment: string;
  score: number;
  description: string;
};

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

type account = {
  id: string;
  email: string;
  role: string;
};

type userDetail = {
  id: string;
  name: string;
  introduction: string;
  phoneNumber: string;
  bootcamp: string;
};

type social = {
  id: string;
  linkedInUrl: string;
  codewarsUrl: string;
};

type github = {
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

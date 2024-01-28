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

export type Scores = { scoreName: string; data: SimpleTableEntry };

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
  scores: Scores[];
  selectedProjects: Project[];
  radarGraph: RadarGraphicData[];
  commitsCount?: number;
  issuesCount?: number;
};

export type DeveloperShowcase = {
  id: string;
  email: string;
  name: string;
  standoutIntro: string;
};

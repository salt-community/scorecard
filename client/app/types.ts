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

import { Average, DetailScores, Score } from "./types";

export const isExcellent = (average: number) => {
  return levelVariant(average) === 3;
};

export const colorVariant = (value: number) => {
  if (value >= 90) {
    return "secondary";
  } else if (value > 70) {
    return "primary";
  } else {
    return "warning";
  }
};
export const levelVariant = (value: number) => {
  if (value >= 90) {
    return 3;
  } else if (value > 70) {
    return 2;
  } else {
    return 1;
  }
};

export function capitalizeEveryWord(inputString: string): string {
  return inputString.replace(/\b\w/g, (match) => match.toUpperCase());
}

export const scoreData = (scores: Score[], averages: Average[]) => {
  const type: string[] = scores?.map((score) => score.type);
  function onlyUnique(value: any, index: any, array: any) {
    return array.indexOf(value) === index;
  }

  var unique = type?.filter(onlyUnique);
  const detailScores: DetailScores[] = [];
  for (let i = 0; i < unique.length; i++) {
    const scoreName = unique[i];
    const scoreData = scores.filter((score) => score.type === scoreName);

    const averageScore = averages.filter(
      (avg) => avg.scoreName === scoreName
    )[0]?.average;

    const detailScore: DetailScores = {
      scoreName: scoreName,
      average: averageScore,
      data: scoreData,
    };
    detailScores.push(detailScore);
  }
  return detailScores;
};

import { DeveloperData, SimpleTableEntry } from "./types";

export const isExcellent = (developerData: DeveloperData) => {
  return levelVariant(getAllAverageValue(developerData.scores)) === 3;
};

export const getAverageValue = (data: SimpleTableEntry) => {
  let sum = 0;
  let count = 0;
  for (const key in data) {
    if (typeof data[key] === "number") {
      sum += data[key] as number;
      count++;
    }
  }
  return sum / count;
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

export const getAllAverageValue = (
  scoreData: { scoreName: string; data: SimpleTableEntry }[]
) => {
  const allAveNum: number[] = [];
  scoreData
    ? scoreData.map((item) => allAveNum.push(getAverageValue(item.data)))
    : allAveNum.push(0);
  const sum = allAveNum.reduce((acc, curr) => acc + curr, 0);
  return sum / allAveNum.length;
};

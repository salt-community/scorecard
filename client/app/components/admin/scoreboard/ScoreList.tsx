"use client";
import React from "react";
import {
  Tabs,
  TabsHeader,
  TabsBody,
  Tab,
  TabPanel,
} from "@material-tailwind/react";
import { Average, DetailScores, Score } from "@/app/types";
import SimpleTableScoreboard from "./SimpleTableScoreboard";

interface ScoreListProps {
  scores: Score[];
  averages: Average[];
  searchScore: Function;
}

const ScoreList = ({ scores, searchScore, averages }: ScoreListProps) => {
  const scoreData = (scores: Score[]) => {
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
      )[0].average;
      const detailScore: DetailScores = {
        scoreName: scoreName,
        average: averageScore,
        data: scoreData,
      };
      detailScores.push(detailScore);
    }
    return detailScores;
  };
  const detailScores = scoreData(scores);

  return (
    <Tabs value="communication">
      <TabsHeader placeholder={undefined}>
        {detailScores.map(({ scoreName }) => (
          <Tab
            placeholder={undefined}
            key={scoreName}
            value={scoreName}
            activeClassName=" text-accent"
          >
            {scoreName}
          </Tab>
        ))}
      </TabsHeader>
      <TabsBody
        placeholder={undefined}
        className=" min-h-[calc(100vh-290px)] overflow-y-auto"
      >
        {detailScores.map(({ scoreName, data }) => (
          <TabPanel key={scoreName} value={scoreName}>
            {data.map((data, index) => (
              <SimpleTableScoreboard
                data={data}
                searchScore={searchScore}
                key={index}
              />
            ))}
          </TabPanel>
        ))}
      </TabsBody>
    </Tabs>
  );
};

export default ScoreList;

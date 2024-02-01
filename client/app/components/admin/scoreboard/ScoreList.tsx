"use client";
import React from "react";
import {
  Tabs,
  TabsHeader,
  TabsBody,
  Tab,
  TabPanel,
} from "@material-tailwind/react";
import { Average, Score } from "@/app/types";
import SimpleTableScoreboard from "./SimpleTableScoreboard";
import { scoreData } from "@/app/utilities";

type ScoreListProps = {
  scores: Score[];
  averages: Average[];
  searchScore: Function;
};

const ScoreList = ({ scores, searchScore, averages }: ScoreListProps) => {
  const detailScores = scoreData(scores, averages);

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

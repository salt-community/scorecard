"use client";
import React from "react";
import {
  Tabs,
  TabsHeader,
  TabsBody,
  Tab,
  TabPanel,
} from "@material-tailwind/react";
import { DetailScores, Scores } from "@/app/types";
import SimpleTable from "./SimpleTableScoreboard";

interface ScoreboardProps {
  scores: DetailScores[];
}

const ScoreList = ({ scores }: ScoreboardProps) => {
  return (
    <Tabs value="html">
      <TabsHeader placeholder={undefined}>
        {scores.map(({ scoreName }) => (
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
        {scores.map((item) => (
          <TabPanel key={item.scoreName} value={item.scoreName}>
            <SimpleTable data={item.data} />
          </TabPanel>
        ))}
      </TabsBody>
    </Tabs>
  );
};

export default ScoreList;

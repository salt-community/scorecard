"use client";
import React from "react";
import {
  Tabs,
  TabsHeader,
  TabsBody,
  Tab,
  TabPanel,
  TabsStylesType,
} from "@material-tailwind/react";
import { Scores } from "@/app/types";
import SimpleTable from "../../scorecard/SimpleTable";

interface ScoreboardProps {
  scores: Scores[];
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
      <TabsBody placeholder={undefined}>
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

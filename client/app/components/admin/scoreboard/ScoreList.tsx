"use client";
import React from "react";
import {
  Tabs,
  TabsHeader,
  TabsBody,
  Tab,
  TabPanel,
} from "@material-tailwind/react";
import { DetailScores, ScoreRes, Scores, SimpleTableEntry } from "@/app/types";
import SimpleTable from "./SimpleTableScoreboard";

interface ScoreboardProps {
  scores: ScoreRes[];
}

const ScoreList = ({ scores }: ScoreboardProps) => {

  const scoreData = (scores: ScoreRes[]) => {
     const type: string[] = scores.map((score) => score.type);
     function onlyUnique(value:any, index:any, array:any) {
      return array.indexOf(value) === index;
    }
    
    var unique = type.filter(onlyUnique);
    const data: Scores[] = [];
    for (let i = 0; i < unique.length; i++) {
      const scoreName = unique[i];
      const data1 = scores.filter(score => score.type === scoreName)
      .reduce(function(r:any, e) {
        r[e.assignment] = e.score;
        return r;
      }, {});

      const x: Scores = {
        scoreName: scoreName,
        data: data1
      };
      data.push(x); 
    }

    return data;
};
  const a = scoreData(scores); 

  return (
    <Tabs value="html">
      <TabsHeader placeholder={undefined}>
        {a.map(({ scoreName }) => (
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
        {a.map((item) => (
          <TabPanel key={item.scoreName} value={item.scoreName}>
            <SimpleTable data={item.data} />
          </TabPanel>
        ))}
      </TabsBody>
    </Tabs>
  );
};

export default ScoreList;

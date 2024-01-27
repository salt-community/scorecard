import { Card } from "@nextui-org/react";
import React from "react";
import ScoreList from "./ScoreList";

const ScoreboardBody = () => {
  return (
    <div>
      <Card>
        <ScoreList />
      </Card>
    </div>
  );
};

export default ScoreboardBody;

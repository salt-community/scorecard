import {
  Divider,
  ScrollShadow,
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
} from "@nextui-org/react";
import React from "react";
import { Score } from "../../types";
import { capitalizeEveryWord } from "@/app/utilities";

type ScoreEntryProps = {
  data: Score;
};

const ScoreEntry = ({ data }: ScoreEntryProps) => {
  const assignment = data.assignment;
  const score = data.score;
  const description = data.description;
  return (
    <div>
      <Table
        removeWrapper
        hideHeader
        aria-label="Github actions"
        className="w-full -mb-2"
      >
        <TableHeader>
          <TableColumn>key</TableColumn>
          <TableColumn>value</TableColumn>
        </TableHeader>
        <TableBody>
          <TableRow>
            <TableCell>{capitalizeEveryWord(assignment)}</TableCell>
            <TableCell className="text-end">{score}</TableCell>
          </TableRow>
        </TableBody>
      </Table>
      <Divider />
      <ScrollShadow hideScrollBar className="h-[50px] px-3">
        <p className="text-xs text-slate-500">{description}</p>
      </ScrollShadow>
    </div>
  );
};

export default ScoreEntry;

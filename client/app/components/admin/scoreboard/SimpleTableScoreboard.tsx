import { Score } from "@/app/types";
import {
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
} from "@nextui-org/react";
import Link from "next/link";
import React from "react";

type SimpleTableScoreboardProps = {
  data: Score;
  searchScore: Function;
};

const SimpleTableScoreboard = ({
  data,
  searchScore,
}: SimpleTableScoreboardProps) => {
  const assignment = data.assignment;
  const score = data.score;
  const description = data.description;
  return (
    <div>
      <Table
        removeWrapper
        hideHeader
        aria-label="Github actions"
        className="w-full"
      >
        <TableHeader>
          <TableColumn>key</TableColumn>
          <TableColumn>value</TableColumn>
        </TableHeader>
        <TableBody>
          <TableRow>
            <TableCell className=" underline">
              <Link href="" onClick={() => searchScore(assignment)}>
                {assignment}
              </Link>
            </TableCell>
            <TableCell className="text-end">{score}</TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </div>
  );
};

export default SimpleTableScoreboard;

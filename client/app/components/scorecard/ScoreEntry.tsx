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
import { ScoreRes } from "../../types";

export interface SimpleTableProps {
  data: ScoreRes;
}

const ScoreEntry = ({ data }: SimpleTableProps) => {
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
            <TableCell>{data.assignment}</TableCell>
            <TableCell className="text-end">{data.score}</TableCell>
          </TableRow>
        </TableBody>
      </Table>
      <Divider />
      <ScrollShadow hideScrollBar className="h-[50px] px-3">
        <p className="text-xs text-slate-500">{data.description}</p>
      </ScrollShadow>
    </div>
  );
};

export default ScoreEntry;

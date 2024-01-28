import { Score, SimpleTableEntry } from "@/app/types";
import {
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
} from "@nextui-org/react";
import React from "react";

export interface SimpleTableProps {
  data: Score[];
}

const SimpleTable = ({ data }: SimpleTableProps) => {
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
          {data.map((a) => (
            <TableRow key={a.id}>
              <TableCell>{a.assignment}</TableCell>
              <TableCell className="text-end">{a.score.toString()}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default SimpleTable;

import { Score, SimpleTableEntry } from "@/app/types";
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

export interface SimpleTableProps {
  data: Score[];
  searchScore: Function;
}

const SimpleTableScoreboard = ({ data, searchScore }: SimpleTableProps) => {
  const assignment = data.assignment;
  const id = data.id;
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
          {Object.entries(data).map(([key, value], index) => (
            <TableRow key={index}>
              <TableCell className=" underline">
                <Link href="" onClick={() => searchScore(key)}>
                  {key}
                </Link>
              </TableCell>
              <TableCell className="text-end">{value}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default SimpleTableScoreboard;

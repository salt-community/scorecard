import { SimpleTableEntry } from "@/app/types";
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
  data: SimpleTableEntry;
  searchScore: Function;
}

const SimpleTable = ({ data, searchScore }: SimpleTableProps) => {
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

export default SimpleTable;

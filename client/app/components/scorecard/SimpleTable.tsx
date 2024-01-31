import {
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
} from "@nextui-org/react";
import React from "react";
import { SimpleTableEntry } from "../../types";

type SimpleTableProps = {
  data: SimpleTableEntry;
};

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
          {Object.entries(data).map(([key, value], index) => (
            <TableRow key={index}>
              <TableCell>{key}</TableCell>
              <TableCell className="text-end">{value}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default SimpleTable;

import {
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
} from '@nextui-org/react';
import React from 'react';

interface SimpleTableProps {
  data: { Commits: number; Issues: number };
}

const SimpleTable = ({ data }: SimpleTableProps) => {
  return (
    <div>
      <Table removeWrapper hideHeader aria-label="Github actions">
        <TableHeader>
          <TableColumn>key</TableColumn>
          <TableColumn>value</TableColumn>
        </TableHeader>
        <TableBody>
          {Object.entries(data).map(([key, value], index) => (
            <TableRow key={index}>
              <TableCell>{key}</TableCell>
              <TableCell>{value}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default SimpleTable;

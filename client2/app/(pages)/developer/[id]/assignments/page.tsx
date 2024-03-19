"use client";
import { AddAssignmentForm } from "@/app/components/AddAssignmentForm";
import { ListAssignmentsForAccount } from "@/app/components/ListAssignmentsForAccount";
import { postAssignment } from "@/server";
import React, { useState } from "react";

type Assignment = {
  assignmentId: string;
  title: string;
  score: number;
  description: string;
  category: string;
  accountId: string;
};

const AddAssignments = ({ params }: { params: { id: string } }) => {
  const id = params.id as string;

  const [assignments, setAssignments] = useState<Assignment[]>([]);

  return (
    <>
      <AddAssignmentForm developerId={id} postAssignment={postAssignment} setAssignments={setAssignments} />
      <ListAssignmentsForAccount accountId={params.id} setAssignments={setAssignments} assignments={assignments} />
    </>
  );
};

export default AddAssignments;

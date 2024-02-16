"use client";
import { AddAssignmentForm } from "@/app/components/AddAssignmentForm";
import { ListAssignmentsForAccount } from "@/app/components/ListAssignmentsForAccount";
import { postAssignment } from "@/server";
import React from "react";

const AddAssignments = ({ params }: { params: { id: string } }) => {
  const id = params.id as string;

  return (
    <>
      <AddAssignmentForm developerId={id} postAssignment={postAssignment} />
      <ListAssignmentsForAccount developerId={params.id} />
    </>
  );
};

export default AddAssignments;

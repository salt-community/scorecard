"use client";
import { AddAssignmentForm } from "@/app/components/AddAssignmentForm";
import { ListAssignmentsForAccount } from "@/app/components/ListAssignmentsForAccount";
import { postAssignment } from "@/server";

export default function AssignmentPage({ params }: { params: { id: string } }) {
  return (
    <>
      <AddAssignmentForm
        accountId={params.id}
        postAssignment={postAssignment}
      />
      <ListAssignmentsForAccount accountId={params.id} />
    </>
  );
}

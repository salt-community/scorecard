"use client";
import { AddAssignmentForm } from "@/app/components/AddAssignmentForm";
import { ListAssignmentsForAccount } from "@/app/components/ListAssignmentsForAccount";

export default function AssignmentPage({ params }: { params: { id: string } }) {
  return (
    <>
      <AddAssignmentForm accountId={params.id} />
      <ListAssignmentsForAccount accountId={params.id} />
    </>
  );
}

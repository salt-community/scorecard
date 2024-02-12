"use client";
import { AddAssignmentForm } from "@/app/components/AddAssignmentForm";

export default function AssignmentPage({ params }: { params: { id: string } }) {
  return (
    <>
      <p>{params.id}</p>
      <AddAssignmentForm accountId={params.id} />
    </>
  );
}

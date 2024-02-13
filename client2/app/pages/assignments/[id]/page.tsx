"use client";
import { useRouter } from "next/navigation";

export default function AssignmentPage({ params }: { params: { id: string } }) {
  return (
    <>
      <p>{params.id}</p>
    </>
  );
}

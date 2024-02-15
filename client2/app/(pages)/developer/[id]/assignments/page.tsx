"use client"
import { ListAssignmentsForAccount } from '@/app/components/ListAssignmentsForAccount';
import { postAssignment } from '@/server';
import React from 'react'

const AddAssignments = ({ params }: { params: { id: string } }) => {
  const id = params.id as string
  
  return (
    <>
   {/* <AddAssignments /> */}
    <ListAssignmentsForAccount accountId={params.id}/>
    </>
  )
}

export default AddAssignments;
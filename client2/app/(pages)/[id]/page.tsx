"use client"
import { AddDeveloperForm } from '@/app/components/AddDeveloperForm'
import { ListAllDevelopers } from '@/app/components/ListAllDevelopers'
import { postDeveloper } from '@/server'
import React from 'react'

export default function  DeveloperPage ({ params }: { params: { id: string } }) {
  return (
    <>
    <AddDeveloperForm postDeveloper={postDeveloper} />
    <ListAllDevelopers/>
    </>
  )
}

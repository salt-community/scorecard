import React from 'react'

type showCardProps = {
    id: string;
    name: string;
    profilePicture: string;
    standoutIntro: string;
  };

export const ShowCard = ({name, profilePicture, standoutIntro}:showCardProps) => {
  return (
    <div>
        <img src={profilePicture}></img>
        <h3>{name}</h3>
        <p>{standoutIntro}</p>
    </div>
  )
}

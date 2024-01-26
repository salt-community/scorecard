import AllDevelopers from '@/app/components/admin/AllDevelopers'
import React from 'react'

const fetchAllUsers = async () => {
    const res = await fetch(`https://scorecard-l6oa.onrender.com/api/developers`, {
      cache: "no-cache"
    });
    const data = await res.json();
    return data;
  };
  type developerInList = {
    id: string;
    name: string;
    profilePicture: string;
    standoutIntro: string;
  };
const page = async () => {
  const developersData: developerInList[] = await fetchAllUsers();
  return (
    <div className='bg-bannerColor1'>
        <AllDevelopers developers={developersData} />
    </div>
  )
}

export default page
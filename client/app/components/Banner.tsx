import React from 'react'

export const Banner = () => {
  return (
    <div className='w-full flex text-white flex-col items-center justify-center mb-10 relative h-32 bg-gradient-to-r from-bannerColor1 to-bannerColor2'>
        <h1 className='text-2xl text-red-500'>Tommorow's Full-Stack Developers</h1>
        <h1 className='text-4xl'>Available Today</h1>
    </div>
  )
}

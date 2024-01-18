'use client';

import { useState, useEffect } from 'react';
import { Radar, RadarChart, PolarGrid, PolarAngleAxis } from 'recharts';

const data = [
  {
    subject: 'frontend',
    A: 90,
    fullMark: 100,
  },
  {
    subject: 'backend',
    A: 98,
    fullMark: 100,
  },
  {
    subject: 'charismatic',
    A: 86,
    fullMark: 100,
  },
  {
    subject: 'teamwork',
    A: 99,
    fullMark: 100,
  },
  {
    subject: 'design',
    A: 85,
    fullMark: 100,
  },
  {
    subject: 'management',
    A: 65,
    fullMark: 100,
  },
];

export const RadarGraphic = () => {
  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth < 768);
    };

    handleResize();

    window.addEventListener('resize', handleResize);

    // Clean up event listener on unmount
    return () => window.removeEventListener('resize', handleResize);
  }, []);
  return (
    <RadarChart
      cx={isMobile ? 150 : 200}
      cy={125}
      outerRadius={80}
      width={isMobile ? 300 : 400}
      height={250}
      data={data}
    >
      <PolarGrid />
      <PolarAngleAxis
        dataKey="subject"
        tick={{ fontSize: isMobile ? 'x-small' : 'small' }}
      />
      <Radar
        name="Mike"
        dataKey="A"
        stroke="#888888"
        fill="#999999"
        fillOpacity={0.6}
      />
    </RadarChart>
  );
};

'use client';

import { useState, useEffect } from 'react';
import { Radar, RadarChart, PolarGrid, PolarAngleAxis } from 'recharts';
import { RadarGraphicData } from '../types';

export const useIsServerSide = () => {
  const [isServerSide, setIsServerSide] = useState(true);

  useEffect(() => {
    setIsServerSide(false);
  }, [setIsServerSide]);

  return isServerSide;
};

interface RadarGraphicProps {
  data: RadarGraphicData[];
}

export const RadarGraphic = ({ data }: RadarGraphicProps) => {
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
  const isServerSide = useIsServerSide();
  if (isServerSide) return null;
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
        dataKey="score"
        stroke="#888888"
        fill="#999999"
        fillOpacity={0.6}
      />
    </RadarChart>
  );
};

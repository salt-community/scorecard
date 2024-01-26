import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import './globals.css';



const inter = Inter({ subsets: ['latin'] });

export const metadata: Metadata = {
  title: 'Score Card Project',
  description: 'A score card that marks the talents',
};

export default function AdminLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en" className="light">
      <body className={inter.className}>

        {children}
      </body>
    </html>
  );
}

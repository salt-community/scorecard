
import { Inter } from 'next/font/google';
import './global.css';
import { Sidebar } from '../components/admin/Sidebar';
import Topbar from '../components/admin/TopBar';

const inter = Inter({ subsets: ['latin'] });

export default function AdminLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
      <div className={inter.className}>
        <Topbar />
        <Sidebar />
        {children}
      </div>

  );
}

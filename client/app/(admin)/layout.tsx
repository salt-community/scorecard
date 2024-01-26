import { Inter } from "next/font/google";
import "./global.css";
import { Sidebar } from "../components/admin/Sidebar";
import Topbar from "../components/admin/Topbar";
import { Poppins } from "next/font/google";

const poppins = Poppins({
  weight: ["400", "700"],
  subsets: ["latin"],
});

export default function AdminLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en" className=" mx-auto max-w-screen-2xl bg-background">
      <body className={`${poppins.className} flex gap-4`}>
        <aside className="flex-[2]">
          <Sidebar />
        </aside>
        <div className="flex-[8] h-screen">
          <div className="flex flex-col gap-4">
            <div className="flex-[1]">
              <Topbar />
            </div>
            <div className="flex-[8] shadow-xl shadow-blue-gray-900/5 border-2">
              {children}
            </div>
          </div>
        </div>
      </body>
    </html>
  );
}

import "./global.css";
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
    <html lang="en" className="light mx-auto max-w-screen-2xl">
      <body className={poppins.className}>{children}</body>
    </html>
  );
}

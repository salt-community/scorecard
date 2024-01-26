
import { UUID } from 'crypto';
import Link from 'next/link';

// should apply TanStack Query for fetching
const apiUrl = process.env.NEXT_PUBLIC_API_URL;
const fetchAllUsers = async () => {
  const res = await fetch(`https://scorecard-l6oa.onrender.com/api/developers`, {
    cache: "no-cache"
  });
  const data = await res.json();
  console.log(res);
  return data;
};



type developerInList = {
  id: string;
  name: string;
  profilePicture: string;
  standoutIntro: string;
};

export default async function Home() {
  const developersData = await fetchAllUsers();
  console.log(developersData);
  return (
    <main className="flex min-h-screen flex-col items-center p-12 md:p-24">
      <div>
             
         {developersData.map((item: developerInList) => (
          <li key={item.id}>
            <p>---- Card Component ----</p>
            <Link href={`/developers/${item.id}`}>{item.name}</Link>
            <p>---------------------------</p>
          </li>
        ))} 
      </div>
    </main>
  );
}

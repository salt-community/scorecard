const ADMIN_URI = "https://scorecard-l6oa.onrender.com/api/developers";

export const httpGetAllDevelopers = async () => {
  const response = await fetch(ADMIN_URI, { cache: "no-cache" });
  return await response.json();
};

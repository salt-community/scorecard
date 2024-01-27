// const ADMIN_URI = "https://scorecard-l6oa.onrender.com/api/developers";
const ADMIN_URI = "http://localhost:8080/api/developers";

export const httpGetAllDevelopers = async () => {
  const response = await fetch(ADMIN_URI + "/admin", { cache: "no-cache" });
  return await response.json();
};

export const httpGetAllSaltieScoreboard = async () => {
  const response = await fetch(ADMIN_URI + "/admin/scoreboard", {
    cache: "no-cache",
  });
  return await response.json();
};

export const httpGetDeveloperById = async (id: string) => {
  const response = await fetch(ADMIN_URI + "/" + id, { cache: "no-cache" });
  return await response.json();
};

export const httpDeleteDeveloper = async (id: string) => {
  return await fetch(ADMIN_URI, { method: "DELETE" });
};

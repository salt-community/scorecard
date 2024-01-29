const BASIC_URI = "http://localhost:8080";
const ASSIGNMENT_URI = `${BASIC_URI}/api/assignment`;
const DEVELOPERS_URI = `${BASIC_URI}/api/developers`;
const ADMIN_URI = `${DEVELOPERS_URI}/admin`;
const SCORE_URI = `${BASIC_URI}/api/scores`;

//DEVELOPER URI
export const httpGetAllAccounts = async () => {
  const response = await fetch(DEVELOPERS_URI, {
    cache: "no-cache",
  });
  return await response.json();
};

export const httpGetDeveloperById = async (id: string) => {
  const response = await fetch(DEVELOPERS_URI + "/" + id, {
    cache: "no-cache",
  });
  return await response.json();
};

export const httpDeleteDeveloper = async (id: string) => {
  return await fetch(DEVELOPERS_URI, { method: "DELETE" });
};

//ADMIN URI
export const httpGetAllDevelopers = async () => {
  const response = await fetch(ADMIN_URI, { cache: "no-cache" });
  return await response.json();
};

export const httpGetAllSaltieScoreboard = async () => {
  const response = await fetch(ADMIN_URI + "/scoreboard", {
    cache: "no-cache",
  });
  return await response.json();
};

export const httpGetSaltieScoreboard = async (id: string) => {
  const response = await fetch(
    "https://scorecard-server.onrender.com/api/developers/admin/scoreboard/" + id,
    {
      cache: "no-cache",
    }
  );
  return await response.json();
};

//ASSIGNMENT URI
export const httpGetAllAssignment = async () => {
  const response = await fetch(ASSIGNMENT_URI, { cache: "no-cache" });
  return await response.json();
};

export const httpPostScoreById = async (id: string, score: any) => {
  const response = await fetch(
    `https://scorecard-server.onrender.com/api/scores/${id}/add-score`,
    {
      method: "POST",
      body: JSON.stringify(score),
      headers: {
        "content-type": "application/json; charset=utf-8",
      },
    }
  );
  return response.json();
};

export const httpPostDeveloper = async (developer: any) => {
  const response = await fetch(
    `http://localhost:8080/api/accounts`,
    {
      method: "POST",
      body: JSON.stringify(developer),
      headers: {
        "content-type": "application/json; charset=utf-8",
      },
    }
  );
  return response.json();
};
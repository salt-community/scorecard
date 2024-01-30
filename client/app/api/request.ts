const BASIC_URI = process.env.NEXT_PUBLIC_API_URL;
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

export const fetchAllUsers = async () => {
  const res = await fetch(DEVELOPERS_URI, {
    cache: "no-cache",
  });
  const data = await res.json();
  return data;
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

export const httpDeleteDeveloperById = async (id: string) => {
  const response = await fetch(`${DEVELOPERS_URI}/${id}`, {
    method: "DELETE",
  });
  return response;
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

export const httpGetAdmninDeveloperById = async (id: string) => {
  const response = await fetch(ADMIN_URI + "/developer/" + id, {
    cache: "no-cache",
  });
  return await response.json();
};

export const httpGetSaltieScoreboard = async (id: string) => {
  const response = await fetch(ADMIN_URI + "/scoreboard/" + id, {
    cache: "no-cache",
  });
  return await response.json();
};

//ASSIGNMENT URI
export const httpGetAllAssignment = async () => {
  const response = await fetch(ASSIGNMENT_URI, { cache: "no-cache" });
  return await response.json();
};

export const httpPostScoreById = async (id: string, score: any) => {
  const response = await fetch(`${SCORE_URI}/${id}/add-score`, {
    method: "POST",
    body: JSON.stringify(score),
    headers: {
      "content-type": "application/json; charset=utf-8",
    },
  });
  return response.json();
};

export const httpDeleteScoreById = async (id: string) => {
  const response = await fetch(`${SCORE_URI}/${id}`, {
    method: "DELETE",
  });
  return response;
};

export const httpPostDeveloper = async (developer: any) => {
  const response = await fetch(`${BASIC_URI}/api/developers`, {
    method: "POST",
    body: JSON.stringify(developer),
    headers: {
      "content-type": "application/json; charset=utf-8",
    },
  });
  return response;
};

export const httpGetAccountByEmail = async (email: string) => {
  const res = await fetch(`${BASIC_URI}/api/accounts/email/${email}`, {
    cache: "no-cache",
  });
  if (res.status === 500) {
    return null;
  }
  const data = await res.json();
  return data;
};

export const httpCreateAccount = async (account: any) => {
  const response = await fetch(`${BASIC_URI}/api/accounts`, {
    method: "POST",
    body: JSON.stringify(account),
    headers: {
      "content-type": "application/json; charset=utf-8",
    },
  });
  return response;
};

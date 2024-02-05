const BASIC_URI = process.env.NEXT_PUBLIC_API_URL;

//ACCOUNT URI
export const httpGetAccountByEmail = async (email: string) => {
  const response = await fetch(`${BASIC_URI}/api/accounts/email/${email}`, {
    cache: "no-cache",
  });
  return response;
};

export const httpGetAllCoreTeam = async () => {
  const response = await fetch(`${BASIC_URI}/api/accounts/core-team`, {
    cache: "no-cache",
  });
  return await response.json();
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

export const httpDeleteAccountById = async (id: string) => {
  const response = await fetch(`${BASIC_URI}/api/accounts/${id}`, {
    method: "DELETE",
  });
  return response;
};

//DEVELOPER URI
export const httpGetAllAccounts = async () => {
  const response = await fetch(`${BASIC_URI}/api/developers`, {
    cache: "no-cache",
  });
  return await response.json();
};

export const fetchAllUsers = async () => {
  const res = await fetch(`${BASIC_URI}/api/developers`, {
    cache: "no-cache",
  });
  const data = await res.json();
  return data;
};

export const httpGetDeveloperById = async (id: string) => {
  const response = await fetch(`${BASIC_URI}/api/developers/${id}`, {
    cache: "no-cache",
  });
  return await response.json();
};

export const httpDeleteDeveloper = async (id: string) => {
  return await fetch(`${BASIC_URI}/api/developers`, { method: "DELETE" });
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

export const httpDeleteDeveloperById = async (id: string) => {
  const response = await fetch(`${BASIC_URI}/api/developers/${id}`, {
    method: "DELETE",
  });
  return response;
};

//ADMIN URI
export const httpGetAllDevelopers = async () => {
  const response = await fetch(`${BASIC_URI}/api/developers/admin`, {
    cache: "no-cache",
  });
  return await response.json();
};

export const httpGetAllSaltieScoreboard = async () => {
  const response = await fetch(`${BASIC_URI}/api/developers/admin/scoreboard`, {
    cache: "no-cache",
  });
  return await response.json();
};

export const httpGetAdmninDeveloperById = async (id: string) => {
  const response = await fetch(
    `${BASIC_URI}/api/developers/admin/developer/${id}`,
    {
      cache: "no-cache",
    }
  );
  return await response.json();
};

export const httpGetSaltieScoreboard = async (id: string) => {
  const response = await fetch(
    `${BASIC_URI}/api/developers/admin/scoreboard/${id}`,
    {
      cache: "no-cache",
    }
  );
  return await response.json();
};

//ASSIGNMENT URI
export const httpGetAllAssignment = async () => {
  const response = await fetch(`${BASIC_URI}/api/assignment`, {
    cache: "no-cache",
  });
  return await response.json();
};

//SCORE URI
export const httpPostScoreById = async (id: string, score: any) => {
  const response = await fetch(`${BASIC_URI}/api/scores/${id}/add-score`, {
    method: "POST",
    body: JSON.stringify(score),
    headers: {
      "content-type": "application/json; charset=utf-8",
    },
  });
  return response.json();
};

export const httpDeleteScoreById = async (id: string) => {
  const response = await fetch(`${BASIC_URI}/api/scores/${id}`, {
    method: "DELETE",
  });
  return response;
};


export const httpUpdateDeveloperById = async (id: string, developer: any) => {
  const response = await fetch(`${BASIC_URI}/api/developers/${id}`, {
    method: "PUT",
    body: JSON.stringify(developer),
    headers: {
      "content-type": "application/json; charset=utf-8",
    },
  });
  return response;
};

import axios from "axios";

export type PostAssignmentFunction = ({
  accountId,
  assignment,
}: {
  accountId: string;
  assignment: AssignmentFormInfo;
}) => Promise<void>;

type AssignmentFormInfo = {
  title: string;
  score: string;
  description: string;
  category: string;
};

export const postAssignment: PostAssignmentFunction = async ({
  accountId,
  assignment,
}) => {
  try {
    const payload = {
      accountId,
      ...assignment,
    };
    await axios.post("http://localhost:8080/api/v2/assignments", payload, {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    });
  } catch (error) {
    throw new Error(`Error submiting assignment:${String(error)}`);
  }
};

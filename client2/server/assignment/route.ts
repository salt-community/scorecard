import { AssignmentFormInfo } from "@/app/components/AddAssignmentForm";
import axios from "axios";

export type PostAssignmentFunction = ({
  assignment,
}: {
  assignment: AssignmentFormInfo;
}) => Promise<void>;

export type AssignmentToSubmit = {
  developerId: string;
  title: string;
  score: number;
  description: string;
  category: string;
};

export const postAssignment: PostAssignmentFunction = async ({
  assignment,
}) => {
  try {
    const payload = {
      ...assignment,
    };
    await axios.post(`http://localhost:8080/api/v2/assignments`, payload, {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    });
  } catch (error) {
    throw new Error(`Error submiting assignment:${String(error)}`);
  }
};

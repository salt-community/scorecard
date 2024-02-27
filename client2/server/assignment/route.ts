import { AssignmentFormInfo } from "@/app/components/AddAssignmentForm";
import axios from "axios";

export type PostAssignmentFunction = ({
  assignment,
}: {
  assignment: AssignmentFormInfo;
}) => Promise<void>;

export type AssignmentToSubmit = {
  accountId: string;
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
        "Content-Type": "application/json",
      },
    });
  } catch (error) {
    throw new Error(`Error submiting assignment:${String(error)}`);
  }
};

export const getAssignmentsByDeveloperId = async (developerId: string) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v2/assignments/developer/${developerId}`
    );
    if (response.status === 200) {
      return response.data.assignmentResponseList;
    } else {
      throw new Error(
        `Failed to fetch assignments. Status code: ${response.status}`
      );
    }
  } catch (error) {
    throw new Error(`Error getting assignments for developer:${String(error)}`);
  }
};

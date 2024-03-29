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
    const { accountId, ...rest } = assignment;
    const payload = {
      ...rest,
    };
    await axios.post(`http://localhost:8080/api/v2/assignments/${accountId}`, payload, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  } catch (error) {
    throw new Error(`Error submitting assignment:${String(error)}`);
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

export const getScorecardByAccountId = async (accountId: string) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v2/scorecard/${accountId}`
    );
    if (response.status === 200) {
      return response.data;
    } else {
      throw new Error(
        `Failed to fetch scorecard. Status code: ${response.status}`
      );
    }
  } catch (error) {
    throw new Error(`Error getting scorecard for account:${String(error)}`);
  }
};

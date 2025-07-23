import axiosInstance from "./axiosInstance";

export const createTask = async (task) => {
  const url = "/tasks";

  const payload = {
    title: task.title,
    description: task.description,
  };

  const response = await axiosInstance.post(url, payload);
  return response.data;
};

export const getLatestTasks = async () => {
  const url = "/tasks/latest";
  const response = await axiosInstance.get(url);
  return response.data;
};

export const completeTask = async (taskId) => {
  const url = `/tasks/${taskId}/complete`;
  await axiosInstance.patch(url);
  return { id: taskId, completed: true };
};

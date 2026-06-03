import axiosClient from "../api/axiosClient";

export const getConversations = () => {
  return axiosClient.get("/conversations");
};

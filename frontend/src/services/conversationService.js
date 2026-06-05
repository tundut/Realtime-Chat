import axiosClient from "../api/axiosClient";

export const getConversations = () => {
  return axiosClient.get("/conversations");
};

export const getConversationById = (conversationId) => {
  return axiosClient.get(`/conversation/${conversationId}`);
};

export const createConversation = (username) => {
  return axiosClient.post(`/conversation`, null, {
    params: { username },
  });
};

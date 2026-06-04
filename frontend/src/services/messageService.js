import axiosClient from "../api/axiosClient";

export const getMessagesByConversationId = (conversationId) => {
  return axiosClient.get(`/messages/${conversationId}`);
};

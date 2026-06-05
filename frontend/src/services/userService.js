import axiosClient from "../api/axiosClient";

export const searchUsers = (query) => {
  return axiosClient.get("/users/search", {
    params: { q: query },
  });
};

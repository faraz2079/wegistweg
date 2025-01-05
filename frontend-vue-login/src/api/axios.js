import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/users", // Replace with your backend URL
  headers: {
    "Content-Type": "application/json",
  },
});

export default axiosInstance;

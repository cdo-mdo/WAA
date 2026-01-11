import axios from "axios";

export const api = axios.create({
    baseURL: "http://localhost:8080/api", // change if your Spring Boot uses a different port
});

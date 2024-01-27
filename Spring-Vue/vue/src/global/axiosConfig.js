// src/http.js
import axiosConfig from 'axios';

const axios = axiosConfig.create({
  baseURL: 'http://localhost:8080/api',
});

export default axios;

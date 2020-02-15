import axios from 'axios'

axios.defaults.baseURL = '/api/'

axios.interceptors.response.use(response => response, error => {
  alert(error)
  return Promise.reject(error);
});

export const get = (url, config) => {
  return axios.get(url, config)
}

export const post = (url, data, config) => {
  return axios.post(url, data, config)
}

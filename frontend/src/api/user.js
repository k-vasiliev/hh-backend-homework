import { get, post } from './request'

const USER_PATH = '/user'

export const createUser = (userData) => {
  return post(USER_PATH, userData)
}

export const getUsers = (type) => {
  return get(USER_PATH + '?type=' + type)
}

import { get, post } from './request'

const USER_PATH = '/user/'

export const createUser = (userData) => {
  return post(USER_PATH, userData)
}

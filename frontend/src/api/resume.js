import { get, post } from './request'

const RESUME_PATH = '/resume/'

export const createResume = (resumeData) => {
  return post(RESUME_PATH, resumeData)
}

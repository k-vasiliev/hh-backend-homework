import { get, post } from './request'

const RESUME_PATH = '/resume'

export const createResume = (resumeData) => {
  return post(RESUME_PATH, resumeData)
}

export const getResumes = () => {
  return get(RESUME_PATH)
}

import { get, post } from './request'

const VACANCY_PATH = '/vacancy/'

export const createVacancy = (vacancyData) => {
  return post(VACANCY_PATH, vacancyData)
}

export const getVacancies = () => {
  return get(VACANCY_PATH)
}

export const getVacancy = (vacancyId) => {
  return get(VACANCY_PATH + '/' + vacancyId)
}

export const getNegotiations = (vacancyId) => {
  return get(VACANCY_PATH + '/' + vacancyId + '/negotiations')
}

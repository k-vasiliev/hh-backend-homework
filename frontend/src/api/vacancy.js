import { get, post } from './request'

const VACANCY_PATH = '/vacancy/'

export const createVacancy = (vacancyData) => {
  return post(VACANCY_PATH, vacancyData)
}

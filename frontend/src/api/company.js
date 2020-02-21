import { get, post } from './request'

const COMPANY_PATH = '/company'

export const createCompany = (companyData) => {
  return post(COMPANY_PATH, companyData)
}

export const getCompanies = () => {
  return get(COMPANY_PATH)
}

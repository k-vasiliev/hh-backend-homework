import { post } from './request'

const NEGOTIATION_PATH = '/negotiation/'

export const createNegotiation = (negotiationData) => {
  return post(NEGOTIATION_PATH, negotiationData)
}

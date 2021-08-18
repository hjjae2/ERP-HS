import { axios } from '~/api/index';

export default class {
    getCompany(companyId) {
        return axios.get(`/api/v1/companies/${companyId}`);
    }

    getCompanies() {
        return axios.get(`/api/v1/companies`);
    }

    saveCompany(payload) {
        return axios.post(`/api/v1/companies`, payload, {
            withCredentials: true
        })
    }

    updateCompany(companyId, payload) {
        return axios.put(`/api/v1/companies/${companyId}`, payload, {
            withCredentials: true
        })
    }

    deleteCompany(companyId) {
        return axios.delete(`/api/v1/companies/${companyId}`)
    }
}
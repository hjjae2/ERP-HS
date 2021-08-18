import { axios } from '~/api/index';

export default class {
    getWork(workId) {
        return axios.get(`${process.env.API_URL}/api/v1/works/${workId}`);
    };

    getWorks() {
        return axios.get(`${process.env.API_URL}/api/v1/works`);
    };

    saveWork(payload) {
        return axios.post(`${process.env.API_URL}/api/v1/works`, payload, {
            withCredentials: true
        })
    };

    updateWork(workId, payload) {
        return axios.put(`${process.env.API_URL}/api/v1/works/${workId}`, payload, {
            withCredentials: true
        })
    };

    deleteWork(workId) {
        return axios.delete(`${process.env.API_URL}/api/v1/works/${workId}`)
    }
}
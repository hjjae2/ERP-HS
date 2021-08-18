import { axios } from '~/api/index';

export default class {
    getPerson(personId) {
        return axios.get(`${process.env.API_URL}/api/v1/people/${personId}`);
    };

    getPeople() {
        return axios.get(`${process.env.API_URL}/api/v1/people`);
    };

    savePerson(payload) {
        return axios.post(`${process.env.API_URL}/api/v1/people`, payload, {
            withCredentials: true
        })
    };

    updatePerson(personId, payload) {
        return axios.put(`${process.env.API_URL}/api/v1/people/${personId}`, payload, {
            withCredentials: true
        })
    };

    deletePerson(personId) {
        return axios.delete(`${process.env.API_URL}/api/v1/people/${personId}`)
    }    
}
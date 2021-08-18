import PersonAPI from "~/api/person"

const personAPI = new PersonAPI()

export const state = function() {
    return {
        // About data
        list: [],

        // About flag
        isError: false,
    }
}

export const mutations = {
    OCCUR_ERROR(state) {
        state.isError = true
    },
    RESET_ERROR(state) {
        state.isError = false;
    },
    SET_LIST(state, list) {
        state.list = list;
    }
}

export const actions = {
    async getPerson(context, personId) {
        return await personAPI.getPerson(personId);
    },

    async getPeople(context) {
        // return await personAPI.getPeople();
        let people = [];
        try {
            context.commit('RESET_ERROR');
            const response = await personAPI.getPeople();
            people = response.data.data;
        } catch(error) {
            console.log(error);
            context.commit('OCCUR_ERROR');
        } finally {
            context.commit('SET_LIST', people);
        }
    },

    async savePerson(context, payload) {
        return await personAPI.savePerson(payload);
    },

    async updatePerson(context, personId, payload) {
        return await personAPI.updatePerson(personId, payload);
    },

    async deletePerson(context, personId) {
        return await personAPI.deletePerson(personId);
    }
}

export const getters = {
    list(state) {
        return state.list;
    },
    isError(state) {
        return state.isError
    }
}
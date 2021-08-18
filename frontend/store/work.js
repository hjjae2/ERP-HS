import WorkAPI from "~/api/work"

const workAPI = new WorkAPI()

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
    async getWork(context, workId) {
        return await workAPI.getWork(workId);
    },

    async getWorks(context) {
        let works = [];
        try {
            works = await workAPI.getWorks();
        } catch(error) {
            console.log(error);
            context.commit('OCCUR_ERROR');
        } finally {
            context.commit('SET_LIST', works);
        }
    },

    async saveWork(context, payload) {
        return await workAPI.saveWork(payload);
    },

    async updateWork(context, workId, payload) {
        return await workAPI.updateWork(workId, payload);
    },

    async deleteWork(context, workId) {
        return await workAPI.deleteWork(workId);
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
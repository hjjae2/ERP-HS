import CompanyAPI from "~/api/company"

const companyAPI = new CompanyAPI()

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
    async getCompany(context, companyId) {
        return await companyAPI.getCompany(companyAPI);
    },

    async getCompanies(context) {
        let companies = [];
        try {
            context.commit('RESET_ERROR');
            const response = await companyAPI.getCompanies();
            companies = response.data.data
        } catch(error) {
            context.commit('OCCUR_ERROR');
            console.log(error)
        } finally {
            context.commit('SET_LIST', companies);
        }
    },

    async saveCompany(context, payload) {
        return await this.$axios.post(`/api/v1/companies`, payload, {
            withCredentials: true
        })
    },

    async updateCompany(context, companyId, payload) {
        return await this.$axios.put(`/api/v1/companies/${companyId}`, payload, {
            withCredentials: true
        })
    },

    async deleteCompany(context, companyId) {
        return await this.$axios.delete(`/api/v1/companies/${companyId}`)
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
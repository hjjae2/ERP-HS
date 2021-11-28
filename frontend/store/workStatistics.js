import WorkStatisticsAPI from "~/api/workStatistics"

const workStatisticsAPI = new WorkStatisticsAPI()

export const state = function() {
    return {
        // About data
        statisticsOfThisMonth: {},
        statisticsOfMonthlyPrice: {},

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
    SET_STATISTICS_OF_THIS_MONTH(state, data) {
        state.statisticsOfThisMonth = data;
    },
    SET_STATISTICS_OF_MONTHLY_PRICE(state, data) {
        state.statisticsOfMonthlyPrice = data;
    }
}

export const actions = {
    async getWorkStatisticsOfThisMonth(context) {
        return await workStatisticsAPI.getWorkStatisticsOfThisMonth();
    },

    async getWorkStatisticsOfMonthly(context, payload) {
        return await await workStatisticsAPI.getWorkStatisticsOfMonthly(payload);
    },
}

export const getters = {
    statisticsOfThisMonth(state) {
        return state.statisticsOfThisMonth;
    },
    statisticsOfMonthlyPrice(state) {
        return state.statisticsOfMonthlyPrice
    }
}
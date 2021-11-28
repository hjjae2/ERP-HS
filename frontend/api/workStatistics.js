import qs from 'qs';
import { axios } from '~/api/index';

export default class {
    getWorkStatisticsOfThisMonth() {
        return axios.get(`${process.env.API_URL}/api/v1/works/statistics/monthly/this`);
    };

    getWorkStatisticsOfMonthly(params) {
        return axios.get(`${process.env.API_URL}/api/v1/works/statistics/monthly`, 
        { 
            params,
            paramsSerializer: params => {
                return qs.stringify(params, {arrayFormat: 'brackets'})
            }
        });
    };
}
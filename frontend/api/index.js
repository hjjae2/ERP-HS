import Axios from 'axios';

const axios = Axios.create({
    baseURL: `${process.env.API_URL}`,
    timeout: 3000
})

export {
    axios
}


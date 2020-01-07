const axios = require('axios')

const getClima = async (lat, lon) => {
    const instance = axios.create({
        baseURL: `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=ddcfb39cb27b89d37b862f8ff2d3af1f&units=metric`
    });

    let l = await instance.get();


    return l.data.main.temp;

}

module.exports = {
    getClima
}




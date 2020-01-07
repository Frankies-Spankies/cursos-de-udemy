const axios = require('axios')

const getLugar = async (direccion) => {
    const instance = axios.create({
        baseURL: 'https://devru-latitude-longitude-find-v1.p.rapidapi.com/latlon.php?location=' + direccion,
        headers: { 'x-rapidapi-key': 'a4ba0ca6a8mshc42da3d61dfd4f7p170cffjsn6d3f77d712ce' }
    });

    let l = await instance.get();
    console.log(l.data.Results[0].name)


    return l.data.Results[0];

}

module.exports = {
    getLugar
}




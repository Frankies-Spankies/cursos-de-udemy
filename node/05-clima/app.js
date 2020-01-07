const lugar  = require('./lugar/lugar')
const clima  = require('./clima/clima')


const argv = require('yargs').option({
    direccion: {
        alias: 'd',
        desc: 'Direccion de la diudad para obtener el clima',
        demand: true
    }
}).argv;

const direccion = encodeURI(argv.direccion);


const getInfo = async ()=>{
    place = await lugar.getLugar(direccion)
    w = await clima.getClima(place.lat, place.lon)
    return w;

}


getInfo().then(res => console.log(res));






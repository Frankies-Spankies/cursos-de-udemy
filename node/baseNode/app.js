const argv= require('./config/yargs').argv
const colors= require('colors')

const {crearArchivo}=require('./multiplicar');
    
let base=argv.b
console.log(argv.b)

crearArchivo(base).then((resolve)=>{
    console.log(resolve);
});
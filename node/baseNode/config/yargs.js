const opts= {
    base: {
        demand: true,
        alias: 'b',
    }
}
const argv= require('yargs').command('listar', 'imprime',opts ).help().argv;

module.exports={
    argv
}
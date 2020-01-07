const opcionCrear = {
    descripcion: {
        alias: 'd',
        desc: 'Descripcion de la tarea por hacer',
        demand: true
    }
}

const opcionEditar = {
    descripcion: {
        alias: 'd',
        desc: 'Descripcion de la tarea por hacer'
    },
    completado: {
        alias: 'c',
        default: true,
        desc: 'Tarea marcada como completada'
    }
}

const opcionListar = {
    listar:{
        alias: 'l'
    }
}

const argv = require('yargs').command('crear', 'Crear un tarea', opcionCrear)
    .command('actualizar', 'actualiza un tarea', opcionEditar)
    .command('listar', 'Listar tareas', opcionEditar)
    .help().argv;

module.exports = {
    argv
}
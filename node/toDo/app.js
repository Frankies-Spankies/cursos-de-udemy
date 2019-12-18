const argv = require('./config/yargs').argv;
const toDo = require ('./toDo/todo')

let opcion=argv._[0];


switch(opcion){
    case 'crear':
        toDo.cargarDB();
        toDo.crear(argv.descripcion);
        toDo.guardarDB();
        break;
    case 'Actualizar':
        console.log('Actualizar tarea')
        break;
    case 'listar':
        toDo.cargarDB();
        toDo.listar();
        break;
    case 'crear':
        console.log('Crear tarea')
        break;
    default:
        console.log('Comando sin reconocer')
}


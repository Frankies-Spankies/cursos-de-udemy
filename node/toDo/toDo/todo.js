const fs = require('fs')
let listaToDo = []

function crear(descripcion) {
    let tarea = {
        descripcion: descripcion,
        completado: false
    }
    listaToDo.push(tarea);
}

function guardarDB() {
    let data = JSON.stringify(listaToDo);
    fs.writeFile('./db/data.json', data, (err) => {
        if (err){

            throw Error ;
        }
    });
    console.log('Archivo guardado');
}

function cargarDB(){
    listaToDo = require('../db/data.json')
}

function listar(){
    listaToDo.map(tarea=>{
        console.log(tarea);
    })
}

function actualizar(){
    cargarDB();
    

}



module.exports = {
    crear,
    listaToDo,
    guardarDB,
    cargarDB,
    listar
}
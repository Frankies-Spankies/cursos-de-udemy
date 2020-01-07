const fs = require('fs');
const colors= require('colors')
let data = '-----------'.green +'Tabla de multiplicar'.white +'-----------'.red + '\n'



function crearArchivo(base) {
    return new Promise((resolve, reject) => {


        for (i = 0; i < 10; i++) {
            data += `i = ${base * i}\n`
        }

        fs.writeFile('message.txt', data, (err) => {
            if (err) {
                reject(err);
                
            } else {
                
                resolve('The file has been saved!')
            }

        });


    });
}

module.exports = {
    crearArchivo
}





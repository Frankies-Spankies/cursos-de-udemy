const http = require('http')

http.createServer((req, res)=>{
    res.writeHead(200, {'Content-Type': 'application/json'})
    let hola={
        nombre: 'FrankiPanki',
        animal: 'Panda'
    }
    res.write(JSON.stringify(hola))
    res.end();

}).listen(8080)

console.log('Escuchando puerto 8080')
const express = require('express')
const app = express()
const hbs = require('hbs')
const port = process.env.PORT || 3000;

require('./hbs/helper') ;

app.use(express.static(__dirname +  '/public'));

app.set('view engine', 'hbs');

hbs.registerPartials(__dirname + '/views/parciales')

app.get('/',  (req, res) => {

    res.render('home',{
        nombre: 'FrankiPanki'
    })

})
 
app.get('/about',  (req, res) => {

    res.render('about');

})

app.listen(port, ()=>{
    console.log(`Peticiones en el puerto ${port}`);
})
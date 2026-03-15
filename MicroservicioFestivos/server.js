const express = require('express');
const festivosRutas = require('./src/rutas/festivos.rutas');
const conectarBD = require('./src/baseDatos/conexion');

const app = express();

app.use(express.json());

conectarBD();

app.use('/api', festivosRutas);

app.get('/', (req, res) => {
    res.send("Microservicio Festivos funcionando");
});

app.listen(3000, () => {
    console.log("Servidor corriendo en el puerto 3000");
});
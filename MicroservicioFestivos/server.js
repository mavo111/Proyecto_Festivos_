const express = require('express');
const festivosRoutes = require('./src/routes/festivos.routes');

const app = express();

app.use('/api', festivosRoutes);

app.get('/', (req, res) => {
    res.send("Microservicio Festivos funcionando");
});

app.listen(3000, () => {
    console.log("Servidor corriendo en el puerto 3000");
});
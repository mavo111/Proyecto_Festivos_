const express = require('express');
const router = express.Router();

const festivosControlador = require('../controladores/festivos.controlador');

router.get('/festivos', festivosControlador.obtenerFestivos);
router.get('/festivos/obtener/:anio', festivosControlador.obtenerFestivosPorAnio);
router.get('/pascua/:anio', festivosControlador.calcularPascua);

module.exports = router;
const express = require('express');
const router = express.Router();

const festivosController = require('../controllers/festivos.controller');

router.get('/festivos', festivosController.obtenerFestivos);

module.exports = router;
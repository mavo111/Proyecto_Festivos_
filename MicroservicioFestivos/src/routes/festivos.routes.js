const express = require('express');
const router = express.Router();

router.get('/festivos', (req, res) => {
    res.json({
        mensaje: "Ruta de festivos funcionando"
    });
});

module.exports = router;
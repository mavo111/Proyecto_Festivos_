const festivosServicio = require('../servicios/festivos.servicio');

exports.obtenerFestivos = (req, res) => {

    const festivos = festivosServicio.obtenerFestivos();

    res.json(festivos);

};

exports.calcularPascua = (req, res) => {

    const anio = parseInt(req.params.anio);

    const fechaPascua = festivosServicio.calcularPascua(anio);

    res.json({
        anio: anio,
        pascua: fechaPascua
    });

};

exports.obtenerFestivosPorAnio = async (req, res) => {

    try {

        const anio = parseInt(req.params.anio);

        const festivos = await festivosServicio.generarFestivosDelAnio(anio);

        res.json(festivos);

    } catch (error) {

        res.status(500).json({
            error: "Error obteniendo los festivos"
        });

    }

};
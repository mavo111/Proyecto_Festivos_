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

exports.verificarFestivo = async (req, res) => {

    try {

        const anio = parseInt(req.params.anio);
        const mes = parseInt(req.params.mes);
        const dia = parseInt(req.params.dia);

        const festivos = await festivosServicio.generarFestivosDelAnio(anio);

        let esFestivo = false;

        festivos.forEach(f => {

            if (Number(f.dia) === Number(dia) && Number(f.mes) === Number(mes)) {
                esFestivo = true;
            }

        });

        res.json(esFestivo);

    } catch (error) {

        res.status(500).json({
            error: "Error verificando la fecha"
        });

    }

};
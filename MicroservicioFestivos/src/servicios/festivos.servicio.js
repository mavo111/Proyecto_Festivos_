const Tipo = require('../modelos/tipo.modelo');

exports.obtenerFestivos = () => {

    return [
        { nombre: "Año Nuevo", mes: 1, dia: 1 },
        { nombre: "Día del Trabajo", mes: 5, dia: 1 },
        { nombre: "Independencia de Colombia", mes: 7, dia: 20 }
    ];

};

exports.calcularPascua = (anio) => {

    let a = anio % 19;
    let b = anio % 4;
    let c = anio % 7;
    let d = (19 * a + 24) % 30;

    let dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

    let domingoRamos = new Date(anio, 2, 15);
    domingoRamos.setDate(domingoRamos.getDate() + dias);

    let domingoPascua = new Date(domingoRamos);
    domingoPascua.setDate(domingoRamos.getDate() + 7);

    return domingoPascua;

};

exports.obtenerTiposFestivos = async () => {

    try {

        const tipos = await Tipo.find();

        return tipos;

    } catch (error) {

        console.error("Error consultando tipos de festivos:", error);
        throw error;

    }

};

exports.generarFestivosDelAnio = async (anio) => {

    const tipos = await exports.obtenerTiposFestivos();

    let festivos = [];

    tipos.forEach(tipo => {

        tipo.festivos.forEach(f => {

            festivos.push({
                nombre: f.nombre,
                dia: parseInt(f.dia),
                mes: parseInt(f.mes)
            });

        });

    });

    return festivos;


};
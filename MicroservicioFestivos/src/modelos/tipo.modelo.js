const mongoose = require('mongoose');

const FestivoSchema = new mongoose.Schema({
    dia: Number,
    mes: Number,
    nombre: String,
    diasPascua: Number
});

const TipoSchema = new mongoose.Schema({
    id: Number,
    tipo: String,
    modoCalculo: String,
    festivos: [FestivoSchema]
},
{
    collection: 'tipos'
});

module.exports = mongoose.model('Tipo', TipoSchema);
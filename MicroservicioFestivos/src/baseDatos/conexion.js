const mongoose = require('mongoose');

const conectarBD = async () => {

    try {

        await mongoose.connect('mongodb://localhost:27017/BDFestivos');

        console.log("MongoDB conectado");

    } catch (error) {

        console.error("Error conectando MongoDB:", error);
        process.exit(1);

    }

}

module.exports = conectarBD;
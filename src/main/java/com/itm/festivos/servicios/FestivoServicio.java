package com.itm.festivos.servicios;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import com.itm.festivos.entidades.Festivo;
import com.itm.festivos.repositorios.FestivoRepositorio;

@Service
public class FestivoServicio {

    @Autowired
    private FestivoRepositorio festivoRepositorio;

    public LocalDate calcularPascua(int anio){

        int a = anio % 19;
        int b = anio % 4;
        int c = anio % 7;
        int d = (19 * a + 24) % 30;

        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        LocalDate domingoRamos = LocalDate.of(anio, 3, 15).plusDays(dias);

        LocalDate domingoPascua = domingoRamos.plusDays(7);

        return domingoPascua;
    }

    public boolean esFestivo(int anio, int mes, int dia){

        LocalDate fechaConsulta = LocalDate.of(anio, mes, dia);

        // Festivos fijos de Colombia
        if ((mes == 1 && dia == 1) ||      // Año nuevo
            (mes == 5 && dia == 1) ||      // Día del trabajo
            (mes == 7 && dia == 20) ||     // Independencia Colombia
            (mes == 8 && dia == 7) ||      // Batalla de Boyacá
            (mes == 12 && dia == 8) ||     // Inmaculada Concepción
            (mes == 12 && dia == 25)) {    // Navidad
            return true;
        }

        // Buscar festivos almacenados en la base de datos
        List<Festivo> festivos = festivoRepositorio.findAll();

        for(Festivo festivo : festivos){

            LocalDate fechaFestivo = LocalDate.of(anio, festivo.getMes(), festivo.getDia());

            if(fechaFestivo.equals(fechaConsulta)){
                return true;
            }

        }

        return false;
    }
}

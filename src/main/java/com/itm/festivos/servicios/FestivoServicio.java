package com.itm.festivos.servicios;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.itm.festivos.entidades.Festivo;
import com.itm.festivos.repositorios.FestivoRepositorio;

@Service
public class FestivoServicio {

    @Autowired
    private FestivoRepositorio festivoRepositorio;

    // Calcular domingo de pascua
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

    // Aplicar Ley Emiliani (mover al lunes)
    public LocalDate aplicarLeyEmiliani(LocalDate fecha){

        if(fecha.getDayOfWeek() != DayOfWeek.MONDAY){

            int diasParaLunes = 8 - fecha.getDayOfWeek().getValue();

            fecha = fecha.plusDays(diasParaLunes);
        }

        return fecha;
    }

    public boolean esFestivo(int anio, int mes, int dia){

        LocalDate fechaConsulta = LocalDate.of(anio, mes, dia);

        // Festivos fijos de Colombia
        if ((mes == 1 && dia == 1) ||      
            (mes == 5 && dia == 1) ||      
            (mes == 7 && dia == 20) ||     
            (mes == 8 && dia == 7) ||      
            (mes == 12 && dia == 8) ||     
            (mes == 12 && dia == 25)) {    
            return true;
        }

        List<Festivo> festivos = festivoRepositorio.findAll();

        LocalDate pascua = calcularPascua(anio);

        for(Festivo festivo : festivos){

            LocalDate fechaFestivo;

            // Tipo 1 = fijo
            if(festivo.getDiasPascua() == 0){

                fechaFestivo = LocalDate.of(anio, festivo.getMes(), festivo.getDia());

            } 
            // Tipo 2 y 3 = basado en pascua
            else{

                fechaFestivo = pascua.plusDays(festivo.getDiasPascua());
            }

            // aplicar ley emiliani
            fechaFestivo = aplicarLeyEmiliani(fechaFestivo);

            if(fechaFestivo.equals(fechaConsulta)){
                return true;
            }
        }

        return false;
    }
}

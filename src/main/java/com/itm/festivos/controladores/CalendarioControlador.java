package com.itm.festivos.controladores;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.itm.festivos.servicios.FestivoCliente;
import com.itm.festivos.servicios.FestivoServicio;
import com.itm.festivos.repositorios.CalendarioRepositorio;
import com.itm.festivos.entidades.Calendario;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioControlador {

    @Autowired
    private FestivoServicio festivoServicio;

    @Autowired
    private FestivoCliente festivoCliente;

    @Autowired
    private CalendarioRepositorio calendarioRepositorio;

    
   @GetMapping("/verificar/{pais}/{anio}/{mes}/{dia}")
public String verificar(
        @PathVariable int pais,
        @PathVariable int anio,
        @PathVariable int mes,
        @PathVariable int dia) {

    try {

        
        LocalDate.of(anio, mes, dia);

        boolean esFestivo = festivoCliente.esFestivo(anio, mes, dia);

        if (esFestivo) {
            return "Es festivo";
        } else {
            return "No es festivo";
        }

    } catch (Exception e) {
        return "Fecha no válida";
    }
}
    
    @GetMapping("/pascua/{anio}")
    public LocalDate calcularPascua(@PathVariable int anio) {
        return festivoServicio.calcularPascua(anio);
    }

    
    @GetMapping("/festivos/{pais}/{anio}")
    public List<String> listarFestivos(
            @PathVariable int pais,
            @PathVariable int anio) {

        List<String> lista = new ArrayList<>();

        lista.add("Año nuevo - " + anio + "-01-01");
        lista.add("Día del trabajo - " + anio + "-05-01");
        lista.add("Independencia Colombia - " + anio + "-07-20");
        lista.add("Batalla de Boyacá - " + anio + "-08-07");
        lista.add("Navidad - " + anio + "-12-25");

        return lista;
    }

    
    @GetMapping("/generar/{pais}/{anio}")
    public boolean generarCalendario(
            @PathVariable int pais,
            @PathVariable int anio) {

        LocalDate fecha = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);

        while (!fecha.isAfter(fin)) {

            String tipo;

            if (festivoCliente.esFestivo(anio, fecha.getMonthValue(), fecha.getDayOfMonth())) {
                tipo = "Festivo";
            }
            else if (fecha.getDayOfWeek().toString().equals("SATURDAY") ||
                     fecha.getDayOfWeek().toString().equals("SUNDAY")) {
                tipo = "Fin de semana";
            }
            else {
                tipo = "Laboral";
            }

            Calendario calendario = new Calendario();
            calendario.setFecha(fecha);
            calendario.setDescripcion(tipo);

            calendarioRepositorio.save(calendario);

            fecha = fecha.plusDays(1);
        }

        return true;
    }

    
    @GetMapping("/listar/{pais}/{anio}")
    public List<String> listarCalendario(
            @PathVariable int pais,
            @PathVariable int anio) {

        List<String> lista = new ArrayList<>();

        List<Calendario> registros = calendarioRepositorio.findAll();

        for (Calendario c : registros) {

            if (c.getFecha().getYear() == anio) {

                String diaSemana = c.getFecha().getDayOfWeek()
                        .getDisplayName(java.time.format.TextStyle.FULL, new java.util.Locale("es", "ES"));

                lista.add(c.getFecha() + " - " + diaSemana + " - " + c.getDescripcion());
            }
        }

        return lista;
    }
}
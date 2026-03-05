package com.itm.festivos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itm.festivos.entidades.Calendario;

public interface CalendarioRepositorio extends JpaRepository<Calendario, Long> {

}
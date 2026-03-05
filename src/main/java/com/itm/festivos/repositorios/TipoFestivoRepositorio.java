package com.itm.festivos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itm.festivos.entidades.TipoFestivo;

public interface TipoFestivoRepositorio extends JpaRepository<TipoFestivo, Long> {

}
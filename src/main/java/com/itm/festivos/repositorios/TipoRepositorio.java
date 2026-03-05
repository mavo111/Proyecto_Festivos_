package com.itm.festivos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itm.festivos.entidades.Tipo;

public interface TipoRepositorio extends JpaRepository<Tipo, Long> {

}
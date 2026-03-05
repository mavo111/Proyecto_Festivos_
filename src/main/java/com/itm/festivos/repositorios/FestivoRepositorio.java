package com.itm.festivos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itm.festivos.entidades.Festivo;
import java.util.List;

public interface FestivoRepositorio extends JpaRepository<Festivo, Long> {

    List<Festivo> findByPaisId(Long paisId);

}
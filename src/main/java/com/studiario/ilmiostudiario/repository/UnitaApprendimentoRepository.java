package com.studiario.ilmiostudiario.repository;

import com.studiario.ilmiostudiario.model.Argomento;
import com.studiario.ilmiostudiario.model.UnitaApprendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnitaApprendimentoRepository extends JpaRepository<UnitaApprendimento,Integer> {

    List<UnitaApprendimento> findUnitaApprendimentoByArgomenti(Argomento argomento);
    List<UnitaApprendimento> findByArgomentiAndNomeContaining(Argomento argomento, String nameSearch);
}

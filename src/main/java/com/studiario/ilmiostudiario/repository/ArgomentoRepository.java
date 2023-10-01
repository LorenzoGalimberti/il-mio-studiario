package com.studiario.ilmiostudiario.repository;

import com.studiario.ilmiostudiario.model.Argomento;
import com.studiario.ilmiostudiario.model.Nozione;
import com.studiario.ilmiostudiario.model.UnitaApprendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArgomentoRepository extends JpaRepository<Argomento,Integer> {
    Optional<Argomento> findBySlug(String slug);
    List<Argomento> findByNomeContaining(String nameSearch);
}

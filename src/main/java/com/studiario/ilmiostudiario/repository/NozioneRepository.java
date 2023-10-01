package com.studiario.ilmiostudiario.repository;

import com.studiario.ilmiostudiario.model.Nozione;
import com.studiario.ilmiostudiario.model.UnitaApprendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NozioneRepository extends JpaRepository<Nozione,Integer> {

    // Define a custom query method
    Optional<Nozione> findByIdAndUnitaApprendimento(Integer nozione_id, UnitaApprendimento unitaApprendimento);
}
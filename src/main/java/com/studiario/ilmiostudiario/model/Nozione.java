package com.studiario.ilmiostudiario.model;


import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "nozione")
public class Nozione {
    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "testo", length = 1000, nullable = false)
    //@Pattern ---> per evitare caratteri speciali
    @NotBlank
    private String testo;

    @ManyToOne
    @JoinColumn(name = "unita_apprendimento_id")
    private UnitaApprendimento unitaApprendimento;

    // GETTER AND SETTER


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public UnitaApprendimento getUnitaApprendimento() {
        return unitaApprendimento;
    }

    public void setUnitaApprendimento(UnitaApprendimento unitaApprendimento) {
        this.unitaApprendimento = unitaApprendimento;
    }
}

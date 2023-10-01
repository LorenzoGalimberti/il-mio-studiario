package com.studiario.ilmiostudiario.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "unita_apprendimento")
public class UnitaApprendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer unita_id;

    @Column(name = "nome", length = 100, nullable = false)
    //@Pattern ---> per evitare caratteri speciali
    private String nome;
    @Column(name = "is_completed", nullable = false)
    private boolean completed;
    @OneToMany(mappedBy = "unitaApprendimento", cascade = {CascadeType.REMOVE})
    @Column(nullable = false)
    private List<Nozione> nozioni;

    @ManyToOne
    private Argomento argomenti;


    public Argomento getArgomento() {
        return argomenti;
    }

    public void setArgomento(Argomento argomento) {
        this.argomenti = argomento;
    }

    public UnitaApprendimento() {
        // Set default values here
        this.completed = false;
    }

    public List<Nozione> getNozioni() {
        return nozioni;
    }

    public void setNozioni(List<Nozione> nozioni) {
        this.nozioni = nozioni;
    }

    public Integer getUnita_id() {
        return unita_id;
    }

    public void setUnita_id(Integer unita_id) {
        this.unita_id = unita_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

package com.studiario.ilmiostudiario.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Argomento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer argomento_id;

    @Column(name = "nome", length = 100, nullable = false)
    //@Pattern ---> per evitare caratteri speciali
    private String nome;
    @Column(name = "slug", length = 50, nullable = false)
    private String slug;
    @OneToMany(mappedBy = "argomenti",cascade = CascadeType.REMOVE)
    private List<UnitaApprendimento> unitaApprendimento;


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getArgomento_id() {
        return argomento_id;
    }

    public void setArgomento_id(Integer argomento_id) {
        this.argomento_id = argomento_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<UnitaApprendimento> getUnitaApprendimento() {
        return unitaApprendimento;
    }

    public void setUnitaApprendimento(List<UnitaApprendimento> unitaApprendimento) {
        this.unitaApprendimento = unitaApprendimento;
    }
}

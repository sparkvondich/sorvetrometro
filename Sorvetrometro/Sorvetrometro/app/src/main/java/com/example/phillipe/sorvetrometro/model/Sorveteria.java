package com.example.phillipe.sorvetrometro.model;

/**
 * Created by Phillipe on 20/03/2017.
 */

public class Sorveteria {

    private int id;
    private String nome;

    public Sorveteria() {
    }

    public Sorveteria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}




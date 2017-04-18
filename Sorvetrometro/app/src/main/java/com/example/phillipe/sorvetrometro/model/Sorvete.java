package com.example.phillipe.sorvetrometro.model;

/**
 * Created by Phillipe on 20/03/2017.
 */

public class Sorvete {

    private int id;
    private String nome;
    private Sorveteria sorveteria;

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


    public Sorveteria getSorveteria() {
        return sorveteria;
    }

    public void setSorveteria(Sorveteria sorveteria) {
        this.sorveteria = sorveteria;
    }
}

    
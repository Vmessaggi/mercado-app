package com.vmessaggi.mercado;

public class Produto {
    private String nome;
    private String categoria;
    private String unidadeMedida;

    public Produto(String nome, String categoria, String unidadeMedida){
        this.nome = nome;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
    }

    public String getNome(){
        return nome;
    }

    public String getCategoria(){
        return categoria;
    }

    public String getUnidadeMedida(){
        return unidadeMedida;
    }
}

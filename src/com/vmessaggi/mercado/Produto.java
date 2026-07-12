package com.vmessaggi.mercado;

public class Produto {
    private String nome;
    private String categoria;
    private String unidadeMedida;
    private double quantidadeMinima;

    public Produto(String nome, String categoria, String unidadeMedida, double quantidadeMinima){
        this.nome = nome;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.quantidadeMinima = quantidadeMinima;
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

    public double getQuantidadeMinima() {
        return quantidadeMinima;
    }
}

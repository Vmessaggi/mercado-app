package com.vmessaggi.mercado;

public class Produto {
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }
        Produto outroProduto = (Produto) objeto;
        return nome.equals(outroProduto.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", unidadeMedida='" + unidadeMedida + '\'' +
                ", quantidadeMinima=" + quantidadeMinima +
                '}';
    }
}

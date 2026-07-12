package com.vmessaggi.mercado;

public class ItemCompra {
    private Produto produto;
    private double quantidadeDesejada;
    private boolean comprado;

    public ItemCompra(Produto produto, double quantidadeDesejada){
        this.produto = produto;
        this.quantidadeDesejada = quantidadeDesejada;
        this.comprado = false;
    }

    public Produto getProduto(){
        return produto;
    }

    public double getQuantidadeDesejada(){
        return quantidadeDesejada;
    }

    public boolean isComprado(){
        return comprado;
    }

    public void marcarComoComprado(){
        this.comprado = true;
    }
}

package com.vmessaggi.mercado;

import java.time.LocalDate;

public class ItemEstoque {
    private Produto produto;
    private double quantidade;
    private LocalDate dataCompra;

    public ItemEstoque(Produto produto, double quantidade, LocalDate dataCompra){
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
    }

    public Produto getProduto(){
        return produto;
    }

    public double getQuantidade(){
        return quantidade;
    }

    public LocalDate getDataCompra(){
        return dataCompra;
    }

    public void consumir(double quantidadeConsumida) {
        if (quantidadeConsumida > this.quantidade) {
            throw new IllegalArgumentException(
                    "Não é possível consumir " + quantidadeConsumida + produto.getUnidadeMedida() +
                            ", pois só há " + this.quantidade + produto.getUnidadeMedida() + " em estoque."
            );
        }
        this.quantidade -= quantidadeConsumida;
    }

    public void adicionar(double quantidadeAdicionada) {
        this.quantidade += quantidadeAdicionada;
    }

    public boolean estaAbaixoDoMinimo(){
        return this.quantidade < produto.getQuantidadeMinima();
    }
}

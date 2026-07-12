package com.vmessaggi.mercado;

import java.time.LocalDate;

public class SistemaMercado {

    private Despensa despensa;
    private ListaDeCompras listaDeCompras;
    private Historico historico;

    public SistemaMercado() {
        this.despensa = new Despensa();
        this.listaDeCompras = new ListaDeCompras();
        this.historico = new Historico();
    }

    public void comprarProduto(Produto produto, double quantidade, LocalDate data) {
        ItemEstoque item = new ItemEstoque(produto, quantidade, data);
        despensa.adicionarItem(item);
        historico.registrarCompra(produto, data);
    }

    public void consumirProduto(ItemEstoque item, double quantidade, LocalDate data) {
        item.consumir(quantidade);

        if (item.getQuantidade() == 0) {
            historico.registrarEsgotamento(item.getProduto(), data);
        }
    }

    public void atualizarListaDeCompras() {
        this.listaDeCompras = despensa.gerarListaDeCompras();
    }

    public Despensa getDespensa() {
        return despensa;
    }

    public ListaDeCompras getListaDeCompras() {
        return listaDeCompras;
    }

    public Historico getHistorico() {
        return historico;
    }

}
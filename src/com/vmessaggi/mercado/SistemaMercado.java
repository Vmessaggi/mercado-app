package com.vmessaggi.mercado;

import java.sql.SQLException;
import java.time.LocalDate;

public class SistemaMercado {

    private Despensa despensa;
    private ListaDeCompras listaDeCompras;
    private Historico historico;

    private ProdutoRepository produtoRepository;
    private ItemEstoqueRepository itemEstoqueRepository;
    private HistoricoRepository historicoRepository;

    public SistemaMercado() {
        this.despensa = new Despensa();
        this.listaDeCompras = new ListaDeCompras();
        this.historico = new Historico();

        this.produtoRepository = new ProdutoRepository();
        this.itemEstoqueRepository = new ItemEstoqueRepository();
        this.historicoRepository = new HistoricoRepository();
    }

    public void comprarProduto(Produto produto, double quantidade, LocalDate data) throws SQLException {
        if (produto.getId() == null) {
            produtoRepository.salvar(produto);
        }

        ItemEstoque itemExistente = buscarItemPorProduto(produto);

        if (itemExistente != null) {
            itemExistente.adicionar(quantidade);
            itemEstoqueRepository.atualizarQuantidade(itemExistente);
        } else {
            ItemEstoque novoItem = new ItemEstoque(produto, quantidade, data);
            despensa.adicionarItem(novoItem);
            itemEstoqueRepository.salvar(novoItem);
        }

        historico.registrarCompra(produto, data);
        historicoRepository.salvar(new RegistroHistorico(produto, TipoEvento.COMPRA, data));
    }

    public void consumirProduto(ItemEstoque item, double quantidade, LocalDate data) throws SQLException {
        item.consumir(quantidade);
        itemEstoqueRepository.atualizarQuantidade(item);

        if (item.getQuantidade() == 0) {
            historico.registrarEsgotamento(item.getProduto(), data);
            historicoRepository.salvar(new RegistroHistorico(item.getProduto(), TipoEvento.ESGOTAMENTO, data));
        }
    }

    public void atualizarListaDeCompras() {
        this.listaDeCompras = despensa.gerarListaDeCompras();
    }

    private ItemEstoque buscarItemPorProduto(Produto produto) {
        for (ItemEstoque item : despensa.getItens()) {
            if (item.getProduto().equals(produto)) {
                return item;
            }
        }
        return null;
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
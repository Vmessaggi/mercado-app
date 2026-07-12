package com.vmessaggi.mercado;

import java.util.ArrayList;
import java.util.List;

public class Despensa {
    private List<ItemEstoque> itens;

    public Despensa(){
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemEstoque item){
        itens.add(item);
    }

    public List<ItemEstoque> getItens(){
        return itens;
    }

    public void listarItens(){
        for (ItemEstoque item : itens){
            System.out.println(item.getProduto().getNome() + " - " + item.getQuantidade() + item.getProduto().getUnidadeMedida());
        }
    }

    public ListaDeCompras gerarListaDeCompras() {
        ListaDeCompras lista = new ListaDeCompras();

        for (ItemEstoque item : itens) {
            if (item.estaAbaixoDoMinimo()) {
                double quantidadeNecessaria = item.getProduto().getQuantidadeMinima() - item.getQuantidade();
                ItemCompra itemCompra = new ItemCompra(item.getProduto(), quantidadeNecessaria);
                lista.adicionarItem(itemCompra);
            }
        }

        return lista;
    }
}

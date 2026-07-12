package com.vmessaggi.mercado;

import java.util.ArrayList;
import java.util.List;

public class ListaDeCompras {

    private List<ItemCompra> itens;

    public ListaDeCompras() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCompra item) {
        itens.add(item);
    }

    public void removerItem(ItemCompra item) {
        itens.remove(item);
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public void listarPendentes() {
        for (ItemCompra item : itens) {
            if (!item.isComprado()) {
                System.out.println(item.getProduto().getNome() + " - " + item.getQuantidadeDesejada() + item.getProduto().getUnidadeMedida());
            }
        }
    }

}
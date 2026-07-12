package com.vmessaggi.mercado;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Produto leite = new Produto("Leite", "Laticínios", "L");
        Produto pao = new Produto("Pão", "Padaria", "un");

        ItemCompra compraLeite = new ItemCompra(leite, 2.0);
        ItemCompra compraPao = new ItemCompra(pao, 6.0);

        ListaDeCompras lista = new ListaDeCompras();
        lista.adicionarItem(compraLeite);
        lista.adicionarItem(compraPao);

        System.out.println("Antes de marcar comprado:");
        lista.listarPendentes();

        compraLeite.marcarComoComprado();

        System.out.println("Depois de marcar o leite como comprado:");
        lista.listarPendentes();
    }
}

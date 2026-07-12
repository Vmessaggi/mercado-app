package com.vmessaggi.mercado;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);
        Produto feijao = new Produto("Feijão", "Grãos", "kg", 0.5);

        ItemEstoque estoqueArroz = new ItemEstoque(arroz, 5.0, LocalDate.now());
        ItemEstoque estoqueFeijao = new ItemEstoque(feijao, 2.0, LocalDate.now());

        Despensa despensa = new Despensa();
        despensa.adicionarItem(estoqueArroz);
        despensa.adicionarItem(estoqueFeijao);

        estoqueFeijao.consumir(1.7);

        ListaDeCompras listaGerada = despensa.gerarListaDeCompras();
        listaGerada.listarPendentes();
    }
}

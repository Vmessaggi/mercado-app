package com.vmessaggi.mercado;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SistemaMercado sistema = new SistemaMercado();

        Produto leite = new Produto("Leite", "Laticínios", "L", 1.0);

        sistema.comprarProduto(leite, 2.0, LocalDate.of(2026, 6, 1));

        ItemEstoque itemLeite = sistema.getDespensa().getItens().get(0);

        try {
            sistema.consumirProduto(itemLeite, 2.0, LocalDate.of(2026, 6, 8));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        sistema.atualizarListaDeCompras();
        sistema.getListaDeCompras().listarPendentes();

        Double ciclo = sistema.getHistorico().calcularCicloMedioDias(leite);
        System.out.println("Ciclo médio: " + ciclo);
    }
}

package com.vmessaggi.mercado;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Produto leite = new Produto("Leite", "Laticínios", "L", 1.0);

        Historico historico = new Historico();

        historico.registrarCompra(leite, LocalDate.of(2026, 6, 1));
        historico.registrarEsgotamento(leite, LocalDate.of(2026, 6, 8));

        historico.registrarCompra(leite, LocalDate.of(2026, 6, 10));
        historico.registrarEsgotamento(leite, LocalDate.of(2026, 6, 20));

        Double cicloMedio = historico.calcularCicloMedioDias(leite);
        System.out.println("Ciclo médio do leite: " + cicloMedio + " dias");

        LocalDate previsao = historico.estimarDataEsgotamento(leite);
        System.out.println("Previsão de esgotamento do leite: " + previsao);
    }
}

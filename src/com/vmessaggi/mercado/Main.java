package com.vmessaggi.mercado;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Produto leite = new Produto("Leite", "Laticínios", "L", 1.0);
        ItemEstoque estoque = new ItemEstoque(leite, 1.0, LocalDate.now());

        try {
            estoque.consumir(1);
            System.out.println("Consumo realizado! Quantidade atual: " + estoque.getQuantidade());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao consumir: " + e.getMessage());
        }

        System.out.println("Programa continua rodando normalmente depois do erro.");
    }
}

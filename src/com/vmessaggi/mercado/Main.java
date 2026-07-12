package com.vmessaggi.mercado;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        SistemaMercado sistema = new SistemaMercado();

        Produto leite = new Produto("Leite", "Laticínios", "L", 1.0);

        sistema.comprarProduto(leite, 2.0, LocalDate.of(2026, 6, 1));

        ItemEstoque itemLeite = sistema.getDespensa().getItens().get(0);

        try {
            sistema.consumirProduto(itemLeite, 1.5, LocalDate.of(2026, 6, 8));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        sistema.atualizarListaDeCompras();
        sistema.getListaDeCompras().listarPendentes();

        Double ciclo = sistema.getHistorico().calcularCicloMedioDias(leite);
        System.out.println("Ciclo médio: " + ciclo);

        CalculadoraUrgencia calculadoraSimples = new UrgenciaPorEstoqueMinimo();
        exibirUrgencia(itemLeite, calculadoraSimples);

        CalculadoraUrgencia calculadoraPrevisao = new UrgenciaPorPrevisao(sistema.getHistorico());
        exibirUrgencia(itemLeite, calculadoraPrevisao);

        // Testando persistência em arquivo
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(leite);
        produtos.add(arroz);

        ArmazenamentoProdutos armazenamento = new ArmazenamentoProdutos();
        armazenamento.salvar(produtos, "produtos.csv");

        List<Produto> produtosCarregados = armazenamento.carregar("produtos.csv");
        System.out.println("--- Produtos carregados do arquivo ---");
        for (Produto p : produtosCarregados) {
            System.out.println(p.getNome() + " - " + p.getCategoria() + " - " + p.getQuantidadeMinima());
        }
    }

    public static void exibirUrgencia(ItemEstoque item, CalculadoraUrgencia calculadora) {
        int nivel = calculadora.calcularNivelUrgencia(item);
        System.out.println(item.getProduto().getNome() + " - nível de urgência: " + nivel);
    }

}
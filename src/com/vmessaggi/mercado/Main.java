package com.vmessaggi.mercado;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SistemaMercado sistema = new SistemaMercado();
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarProduto(sistema, scanner);
                    break;
                case 2:
                    listarDespensa(sistema);
                    break;
                case 3:
                    consumirItem(sistema, scanner);
                    break;
                case 4:
                    verListaDeCompras(sistema);
                    break;
                case 5:
                    verPrevisao(sistema, scanner);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Encerrando o sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }

        scanner.close();
    }

    public static ItemEstoque buscarItemPorNome(SistemaMercado sistema, String nome) {
        for (ItemEstoque item : sistema.getDespensa().getItens()) {
            if (item.getProduto().getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }

    public static void consumirItem(SistemaMercado sistema, Scanner scanner) {
        scanner.nextLine();
        System.out.print("Nome do produto a consumir: ");
        String nome = scanner.nextLine();

        ItemEstoque item = buscarItemPorNome(sistema, nome);

        if (item == null) {
            System.out.println("Produto não encontrado na despensa.");
            return;
        }

        System.out.print("Quantidade a consumir: ");
        double quantidade = scanner.nextDouble();

        try {
            sistema.consumirProduto(item, quantidade, LocalDate.now());
            System.out.println("Consumo registrado! Quantidade atual: " + item.getQuantidade());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void verListaDeCompras(SistemaMercado sistema) {
        sistema.atualizarListaDeCompras();
        System.out.println("\n--- Lista de compras ---");
        sistema.getListaDeCompras().listarPendentes();
    }

    public static void verPrevisao(SistemaMercado sistema, Scanner scanner) {
        scanner.nextLine();
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        ItemEstoque item = buscarItemPorNome(sistema, nome);

        if (item == null) {
            System.out.println("Produto não encontrado na despensa.");
            return;
        }

        LocalDate previsao = sistema.getHistorico().estimarDataEsgotamento(item.getProduto());

        if (previsao == null) {
            System.out.println("Ainda não há histórico suficiente para prever o esgotamento desse produto.");
        } else {
            System.out.println("Previsão de esgotamento: " + previsao);
        }
    }

    public static void exibirMenu() {
        System.out.println("\n=== Sistema de Mercado ===");
        System.out.println("1 - Cadastrar produto e comprar");
        System.out.println("2 - Listar despensa");
        System.out.println("3 - Consumir item da despensa");
        System.out.println("4 - Ver lista de compras");
        System.out.println("5 - Ver previsão de esgotamento");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarProduto(SistemaMercado sistema, Scanner scanner) {
        scanner.nextLine();

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Unidade de medida (kg, L, un): ");
        String unidade = scanner.nextLine();

        System.out.print("Quantidade mínima: ");
        double minimo = scanner.nextDouble();

        System.out.print("Quantidade comprada agora: ");
        double quantidade = scanner.nextDouble();

        Produto produto = new Produto(nome, categoria, unidade, minimo);
        sistema.comprarProduto(produto, quantidade, LocalDate.now());

        System.out.println("Produto cadastrado e adicionado à despensa!");
    }

    public static void listarDespensa(SistemaMercado sistema) {
        System.out.println("\n--- Itens na despensa ---");
        for (ItemEstoque item : sistema.getDespensa().getItens()) {
            System.out.println(item.getProduto().getNome() + " - " + item.getQuantidade() + item.getProduto().getUnidadeMedida());
        }
    }

}
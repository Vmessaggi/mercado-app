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

    public static void exibirMenu() {
        System.out.println("\n=== Sistema de Mercado ===");
        System.out.println("1 - Cadastrar produto e comprar");
        System.out.println("2 - Listar despensa");
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
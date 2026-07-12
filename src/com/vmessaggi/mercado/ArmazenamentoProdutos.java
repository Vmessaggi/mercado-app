package com.vmessaggi.mercado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoProdutos {

    public void salvar(List<Produto> produtos, String caminhoArquivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));

        for (Produto produto : produtos) {
            String linha = produto.getNome() + "," + produto.getCategoria() + "," +
                    produto.getUnidadeMedida() + "," + produto.getQuantidadeMinima();
            writer.write(linha);
            writer.newLine();
        }

        writer.close();
    }

    public List<Produto> carregar(String caminhoArquivo) throws IOException {
        List<Produto> produtos = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));

        String linha = reader.readLine();
        while (linha != null) {
            String[] partes = linha.split(",");
            String nome = partes[0];
            String categoria = partes[1];
            String unidade = partes[2];
            double minimo = Double.parseDouble(partes[3]);

            produtos.add(new Produto(nome, categoria, unidade, minimo));
            linha = reader.readLine();
        }

        reader.close();
        return produtos;
    }

}
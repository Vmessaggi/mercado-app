package com.vmessaggi.mercado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, categoria, unidade_medida, quantidade_minima) VALUES (?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(
                ConfiguracaoBanco.getUrl(), ConfiguracaoBanco.getUsuario(), ConfiguracaoBanco.getSenha());
             PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getCategoria());
            statement.setString(3, produto.getUnidadeMedida());
            statement.setDouble(4, produto.getQuantidadeMinima());

            statement.executeUpdate();

            try (ResultSet chavesGeradas = statement.getGeneratedKeys()) {
                if (chavesGeradas.next()) {
                    produto.setId(chavesGeradas.getInt(1));
                }
            }
        }
    }

    public List<Produto> listarTodos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT nome, categoria, unidade_medida, quantidade_minima FROM produtos";

        try (Connection conexao = DriverManager.getConnection(
                ConfiguracaoBanco.getUrl(), ConfiguracaoBanco.getUsuario(), ConfiguracaoBanco.getSenha());
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                String nome = resultado.getString("nome");
                String categoria = resultado.getString("categoria");
                String unidade = resultado.getString("unidade_medida");
                double minimo = resultado.getDouble("quantidade_minima");

                produtos.add(new Produto(nome, categoria, unidade, minimo));
            }
        }

        return produtos;
    }

}

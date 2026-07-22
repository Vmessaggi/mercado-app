package com.vmessaggi.mercado;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemEstoqueRepository {

    public void salvar(ItemEstoque item) throws SQLException {
        String sql = "INSERT INTO item_estoque (produto_id, quantidade, data_compra) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(
                ConfiguracaoBanco.getUrl(), ConfiguracaoBanco.getUsuario(), ConfiguracaoBanco.getSenha());
             PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, item.getProduto().getId());
            statement.setDouble(2, item.getQuantidade());
            statement.setDate(3, java.sql.Date.valueOf(item.getDataCompra()));

            statement.executeUpdate();

            try (ResultSet chavesGeradas = statement.getGeneratedKeys()) {
                if (chavesGeradas.next()) {
                    item.setId(chavesGeradas.getInt(1));
                }
            }
        }
    }

    public void atualizarQuantidade(ItemEstoque item) throws SQLException {
        String sql = "UPDATE item_estoque SET quantidade = ? WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(
                ConfiguracaoBanco.getUrl(), ConfiguracaoBanco.getUsuario(), ConfiguracaoBanco.getSenha());
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setDouble(1, item.getQuantidade());
            statement.setInt(2, item.getId());

            statement.executeUpdate();
        }
    }

    public List<ItemEstoque> listarTodos() throws SQLException {
        List<ItemEstoque> itens = new ArrayList<>();
        String sql = "SELECT ie.quantidade, ie.data_compra, " +
                "p.id, p.nome, p.categoria, p.unidade_medida, p.quantidade_minima " +
                "FROM item_estoque ie " +
                "JOIN produtos p ON ie.produto_id = p.id";

        try (Connection conexao = DriverManager.getConnection(
                ConfiguracaoBanco.getUrl(), ConfiguracaoBanco.getUsuario(), ConfiguracaoBanco.getSenha());
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                Produto produto = new Produto(
                        resultado.getString("nome"),
                        resultado.getString("categoria"),
                        resultado.getString("unidade_medida"),
                        resultado.getDouble("quantidade_minima")
                );
                produto.setId(resultado.getInt("id"));

                LocalDate dataCompra = resultado.getDate("data_compra").toLocalDate();

                ItemEstoque item = new ItemEstoque(produto, resultado.getDouble("quantidade"), dataCompra);
                itens.add(item);
            }
        }

        return itens;
    }

}
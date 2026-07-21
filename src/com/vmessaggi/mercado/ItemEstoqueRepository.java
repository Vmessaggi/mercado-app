package com.vmessaggi.mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ItemEstoqueRepository {

    public void salvar(ItemEstoque item) throws SQLException {
        String sql = "INSERT INTO item_estoque (produto_id, quantidade, data_compra) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(
                ConfiguracaoBanco.getUrl(), ConfiguracaoBanco.getUsuario(), ConfiguracaoBanco.getSenha());
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, item.getProduto().getId());
            statement.setDouble(2, item.getQuantidade());
            statement.setDate(3, java.sql.Date.valueOf(item.getDataCompra()));

            statement.executeUpdate();
        }
    }

}
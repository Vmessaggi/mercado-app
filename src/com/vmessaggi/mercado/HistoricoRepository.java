package com.vmessaggi.mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoricoRepository {

    public void salvar(RegistroHistorico registro) throws SQLException {
        String sql = "INSERT INTO registro_historico (produto_id, tipo, data_evento) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(
                ConfiguracaoBanco.getUrl(), ConfiguracaoBanco.getUsuario(), ConfiguracaoBanco.getSenha());
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, registro.getProduto().getId());
            statement.setString(2, registro.getTipo().name());
            statement.setDate(3, java.sql.Date.valueOf(registro.getData()));

            statement.executeUpdate();
        }
    }

}
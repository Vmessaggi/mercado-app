package com.vmessaggi.mercado;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<RegistroHistorico> listarTodos() throws SQLException {
        List<RegistroHistorico> registros = new ArrayList<>();
        String sql = "SELECT rh.tipo, rh.data_evento, " +
                "p.id, p.nome, p.categoria, p.unidade_medida, p.quantidade_minima " +
                "FROM registro_historico rh " +
                "JOIN produtos p ON rh.produto_id = p.id";

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

                TipoEvento tipo = TipoEvento.valueOf(resultado.getString("tipo"));
                LocalDate data = resultado.getDate("data_evento").toLocalDate();

                registros.add(new RegistroHistorico(produto, tipo, data));
            }
        }

        return registros;
    }

}
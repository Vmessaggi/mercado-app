package com.vmessaggi.mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(
                ConfiguracaoBanco.getUrl(),
                ConfiguracaoBanco.getUsuario(),
                ConfiguracaoBanco.getSenha()
        );
    }

}
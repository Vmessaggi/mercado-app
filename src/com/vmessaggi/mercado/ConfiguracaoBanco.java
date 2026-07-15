package com.vmessaggi.mercado;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfiguracaoBanco {

    private static Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível carregar config.properties", e);
        }
    }

    public static String getUrl() {
        return properties.getProperty("db.url");
    }

    public static String getUsuario() {
        return properties.getProperty("db.usuario");
    }

    public static String getSenha() {
        return properties.getProperty("db.senha");
    }

}
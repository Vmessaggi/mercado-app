package com.vmessaggi.mercado;

import java.time.LocalDate;

public class RegistroHistorico {

    private Produto produto;
    private TipoEvento tipo;
    private LocalDate data;

    public RegistroHistorico(Produto produto, TipoEvento tipo, LocalDate data) {
        this.produto = produto;
        this.tipo = tipo;
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

}
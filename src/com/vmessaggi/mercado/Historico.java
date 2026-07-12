package com.vmessaggi.mercado;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Historico {

    private List<RegistroHistorico> registros;

    public Historico() {
        this.registros = new ArrayList<>();
    }

    public void registrarCompra(Produto produto, LocalDate data) {
        registros.add(new RegistroHistorico(produto, TipoEvento.COMPRA, data));
    }

    public void registrarEsgotamento(Produto produto, LocalDate data) {
        registros.add(new RegistroHistorico(produto, TipoEvento.ESGOTAMENTO, data));
    }

    public Double calcularCicloMedioDias(Produto produto) {
        List<Long> duracoes = new ArrayList<>();
        LocalDate ultimaCompra = null;

        for (RegistroHistorico registro : registros) {
            if (registro.getProduto() == produto) {
                if (registro.getTipo() == TipoEvento.COMPRA) {
                    ultimaCompra = registro.getData();
                } else if (registro.getTipo() == TipoEvento.ESGOTAMENTO && ultimaCompra != null) {
                    long dias = ChronoUnit.DAYS.between(ultimaCompra, registro.getData());
                    duracoes.add(dias);
                    ultimaCompra = null;
                }
            }
        }

        if (duracoes.isEmpty()) {
            return null;
        }

        long soma = 0;
        for (long d : duracoes) {
            soma += d;
        }
        return (double) soma / duracoes.size();
    }

    public LocalDate estimarDataEsgotamento(Produto produto) {
        Double cicloMedio = calcularCicloMedioDias(produto);
        if (cicloMedio == null) {
            return null;
        }

        LocalDate ultimaCompra = null;
        for (RegistroHistorico registro : registros) {
            if (registro.getProduto() == produto && registro.getTipo() == TipoEvento.COMPRA) {
                if (ultimaCompra == null || registro.getData().isAfter(ultimaCompra)) {
                    ultimaCompra = registro.getData();
                }
            }
        }

        if (ultimaCompra == null) {
            return null;
        }

        return ultimaCompra.plusDays(Math.round(cicloMedio));
    }
}
package com.vmessaggi.mercado;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UrgenciaPorPrevisao implements CalculadoraUrgencia {

    private Historico historico;

    public UrgenciaPorPrevisao(Historico historico) {
        this.historico = historico;
    }

    @Override
    public int calcularNivelUrgencia(ItemEstoque item) {
        LocalDate previsao = historico.estimarDataEsgotamento(item.getProduto());

        if (previsao == null) {
            return 0;
        }

        long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), previsao);

        if (diasRestantes <= 0) {
            return 2;
        }
        if (diasRestantes <= 3) {
            return 1;
        }
        return 0;
    }

}
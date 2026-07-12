package com.vmessaggi.mercado;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class HistoricoTest {

    @Test
    public void cicloMedioDeveSerCalculadoCorretamente() {
        Produto leite = new Produto("Leite", "Laticínios", "L", 1.0);
        Historico historico = new Historico();

        historico.registrarCompra(leite, LocalDate.of(2026, 6, 1));
        historico.registrarEsgotamento(leite, LocalDate.of(2026, 6, 8));

        historico.registrarCompra(leite, LocalDate.of(2026, 6, 10));
        historico.registrarEsgotamento(leite, LocalDate.of(2026, 6, 20));

        Double ciclo = historico.calcularCicloMedioDias(leite);

        assertEquals(8.5, ciclo);
    }

    @Test
    public void cicloMedioDeveSerNuloSemHistorico() {
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);
        Historico historico = new Historico();

        Double ciclo = historico.calcularCicloMedioDias(arroz);

        assertNull(ciclo);
    }

    @Test
    public void previsaoDeEsgotamentoDeveSerCalculadaCorretamente() {
        Produto leite = new Produto("Leite", "Laticínios", "L", 1.0);
        Historico historico = new Historico();

        historico.registrarCompra(leite, LocalDate.of(2026, 6, 1));
        historico.registrarEsgotamento(leite, LocalDate.of(2026, 6, 8));

        historico.registrarCompra(leite, LocalDate.of(2026, 6, 10));
        historico.registrarEsgotamento(leite, LocalDate.of(2026, 6, 20));

        LocalDate previsao = historico.estimarDataEsgotamento(leite);

        assertEquals(LocalDate.of(2026, 6, 19), previsao);
    }

}
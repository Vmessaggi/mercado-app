package com.vmessaggi.mercado;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraUrgenciaTest {

    @Test
    public void urgenciaPorEstoqueMinimoDeveRetornarZeroQuandoAcimaDoMinimo() {
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);
        ItemEstoque item = new ItemEstoque(arroz, 5.0, LocalDate.now());

        CalculadoraUrgencia calculadora = new UrgenciaPorEstoqueMinimo();

        assertEquals(0, calculadora.calcularNivelUrgencia(item));
    }

    @Test
    public void urgenciaPorEstoqueMinimoDeveRetornarUmQuandoAbaixoDoMinimo() {
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);
        ItemEstoque item = new ItemEstoque(arroz, 0.5, LocalDate.now());

        CalculadoraUrgencia calculadora = new UrgenciaPorEstoqueMinimo();

        assertEquals(1, calculadora.calcularNivelUrgencia(item));
    }

    @Test
    public void urgenciaPorEstoqueMinimoDeveRetornarDoisQuandoZerado() {
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);
        ItemEstoque item = new ItemEstoque(arroz, 0.0, LocalDate.now());

        CalculadoraUrgencia calculadora = new UrgenciaPorEstoqueMinimo();

        assertEquals(2, calculadora.calcularNivelUrgencia(item));
    }

}
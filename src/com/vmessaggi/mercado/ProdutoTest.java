package com.vmessaggi.mercado;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    public void doisProdutosComMesmoNomeDevemSerIguais() {
        Produto leite1 = new Produto("Leite", "Laticínios", "L", 1.0);
        Produto leite2 = new Produto("Leite", "Laticínios", "L", 1.0);

        assertEquals(leite1, leite2);
    }

    @Test
    public void doisProdutosComNomesDiferentesNaoDevemSerIguais() {
        Produto leite = new Produto("Leite", "Laticínios", "L", 1.0);
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);

        assertNotEquals(leite, arroz);
    }

}
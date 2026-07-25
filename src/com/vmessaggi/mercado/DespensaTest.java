package com.vmessaggi.mercado;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DespensaTest {

    @Test
    public void gerarListaDeComprasDeveIncluirItensAbaixoDoMinimo() {
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);
        ItemEstoque itemArroz = new ItemEstoque(arroz, 0.5, LocalDate.now());

        Despensa despensa = new Despensa();
        despensa.adicionarItem(itemArroz);

        ListaDeCompras lista = despensa.gerarListaDeCompras();

        assertEquals(1, lista.getItens().size());
        assertEquals(arroz, lista.getItens().get(0).getProduto());
    }

    @Test
    public void gerarListaDeComprasNaoDeveIncluirItensAcimaDoMinimo() {
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);
        ItemEstoque itemArroz = new ItemEstoque(arroz, 5.0, LocalDate.now());

        Despensa despensa = new Despensa();
        despensa.adicionarItem(itemArroz);

        ListaDeCompras lista = despensa.gerarListaDeCompras();

        assertTrue(lista.getItens().isEmpty());
    }

    @Test
    public void gerarListaDeComprasDeveUsarQuantidadeMinimaComoQuantidadeDesejada() {
        Produto leite = new Produto("Leite", "Laticínios", "L", 2.0);
        ItemEstoque itemLeite = new ItemEstoque(leite, 0.5, LocalDate.now());

        Despensa despensa = new Despensa();
        despensa.adicionarItem(itemLeite);

        ListaDeCompras lista = despensa.gerarListaDeCompras();

        assertEquals(2.0, lista.getItens().get(0).getQuantidadeDesejada());
    }

}
package com.vmessaggi.mercado;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaDeComprasTest {

    @Test
    public void itemMarcadoComoCompradoNaoDeveAparecerNosPendentes() {
        Produto leite = new Produto("Leite", "Laticínios", "L", 1.0);
        ItemCompra itemLeite = new ItemCompra(leite, 2.0);

        ListaDeCompras lista = new ListaDeCompras();
        lista.adicionarItem(itemLeite);

        itemLeite.marcarComoComprado();

        long pendentesRestantes = lista.getItens().stream()
                .filter(item -> !item.isComprado())
                .count();

        assertEquals(0, pendentesRestantes);
    }

    @Test
    public void removerItemDeveTirarDaLista() {
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);
        ItemCompra itemArroz = new ItemCompra(arroz, 1.0);

        ListaDeCompras lista = new ListaDeCompras();
        lista.adicionarItem(itemArroz);
        lista.removerItem(itemArroz);

        assertTrue(lista.getItens().isEmpty());
    }

}
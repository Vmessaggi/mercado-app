package com.vmessaggi.mercado;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ItemEstoqueTest {

    @Test
    public void consumirDeveDiminuirQuantidade() {
        Produto arroz = new Produto("Arroz", "Grãos", "kg", 1.0);
        ItemEstoque item = new ItemEstoque(arroz, 5.0, LocalDate.now());

        item.consumir(2.0);

        assertEquals(3.0, item.getQuantidade());
    }

    @Test
    public void consumirMaisDoQueTemDeveLancarExcecao() {
        Produto leite = new Produto("Leite", "Laticínios", "L", 1.0);
        ItemEstoque item = new ItemEstoque(leite, 1.0, LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> {
            item.consumir(2.0);
        });
    }

}
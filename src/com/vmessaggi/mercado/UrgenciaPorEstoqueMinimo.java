package com.vmessaggi.mercado;

public class UrgenciaPorEstoqueMinimo implements CalculadoraUrgencia {

    @Override
    public int calcularNivelUrgencia(ItemEstoque item) {
        if (item.getQuantidade() <= 0) {
            return 2;
        }
        if (item.estaAbaixoDoMinimo()) {
            return 1;
        }
        return 0;
    }

}
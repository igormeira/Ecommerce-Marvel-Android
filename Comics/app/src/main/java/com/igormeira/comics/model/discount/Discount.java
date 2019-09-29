package com.igormeira.comics.model.discount;

import com.igormeira.comics.model.Comic;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface de desconto.
 */
public interface Discount {

    /**
     * Responsável por calcular o total da compra após desconto.
     * Faz uso do método applyDiscount(BigDecimal price).
     *
     * @param comics
     * @return BigDecimal
     */
    public BigDecimal getDiscount(List<Comic> comics);

    /**
     * Responsável por aplicar desconto em um valor.
     *
     * @param price
     * @return BigDecimal
     */
    public BigDecimal applyDiscount(BigDecimal price);

}

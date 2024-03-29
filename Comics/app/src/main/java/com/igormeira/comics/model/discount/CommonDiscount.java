package com.igormeira.comics.model.discount;

import com.igormeira.comics.model.Comic;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementa a interface Discount.
 * Aplica descontos em comics com o tipo comum.
 */
public class CommonDiscount implements Discount {

    BigDecimal total;
    BigDecimal discount;

    @Override
    public BigDecimal getDiscount(List<Comic> comics) {
        total = new BigDecimal(0.00);
        for (Comic comic: comics) {
            if (comic.getType().equals("Comum")) {
                total = total.add(applyDiscount(comic.getPrice()));
            }
            else {
                total = total.add(comic.getPrice());
            }
        }
        return total;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        discount = new BigDecimal(0.00);
        BigDecimal off = price.multiply(BigDecimal.valueOf(0.10));
        discount = price.subtract(off);
        return discount;
    }
}

package com.igormeira.comics.model.discount;

import com.igormeira.comics.model.Comic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class RareDiscount implements Discount {

    BigDecimal total;
    BigDecimal discount;

    @Override
    public BigDecimal getDiscount(List<Comic> comics) {
        total = new BigDecimal(0.00, MathContext.DECIMAL64);
        for (Comic comic: comics) {
            total = total.add(applyDiscount(comic.getPrice()));
        }
        return total;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        discount = new BigDecimal(0.00, MathContext.DECIMAL64);
        BigDecimal off = price.multiply(BigDecimal.valueOf(0.25));
        discount = price.subtract(off);
        return discount;
    }
}

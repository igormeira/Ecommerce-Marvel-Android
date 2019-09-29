package com.igormeira.comics.model.discount;

import android.util.Log;

import com.igormeira.comics.model.Comic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class WithoutDiscount implements Discount {

    BigDecimal total;

    @Override
    public BigDecimal getDiscount(List<Comic> comics) {
        total = new BigDecimal(0.00, MathContext.DECIMAL64);
        for (Comic comic: comics) {
            total = total.add(comic.getPrice());
        }
        return total;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        return price;
    }
}

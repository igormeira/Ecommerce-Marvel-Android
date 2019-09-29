package com.igormeira.comics.model.discount;

import com.igormeira.comics.model.Comic;

import java.math.BigDecimal;
import java.util.List;

public interface Discount {

    public BigDecimal getDiscount(List<Comic> comics);

    public BigDecimal applyDiscount(BigDecimal price);

}

package com.igormeira.comics;

import com.igormeira.comics.model.Comic;
import com.igormeira.comics.model.discount.CommonDiscount;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommonDiscountUnitTest {

    private Comic commonComic, rareComic;
    private List<Comic> comicsZero, comicsOne, comicsTwo;

    @Before
    public void initValues() {
        commonComic = new Comic("Titulo", "Descricao", BigDecimal.ONE,
                "Thumbnail", "Comum");
        rareComic = new Comic("Titulo", "Descricao", BigDecimal.TEN,
                "Thumbnail", "Raro");

        comicsZero = new ArrayList<>();
        comicsOne = Collections.singletonList(commonComic);
        comicsTwo = Arrays.asList(commonComic, rareComic);
    }

    /**
     * O desconto deve ser aplicado aos quadrinhos do tipo raro.
     * A aplição da regra é feita apenas na função getDiscount().
     */
    @Test
    public void calculateOneItemCommonDiscount() {
        BigDecimal commonExpectedValue = new BigDecimal(0.9);
        commonExpectedValue = commonExpectedValue.setScale(1, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal commonItemPrice = commonComic.getPrice();
        BigDecimal commonItemDiscount = new CommonDiscount().applyDiscount(commonItemPrice);
        assertEquals(commonExpectedValue, commonItemDiscount);

        BigDecimal rareExpectedValue = new BigDecimal(9.0);
        rareExpectedValue = rareExpectedValue.setScale(1, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal rareItemPrice = rareComic.getPrice();
        BigDecimal rareItemDiscount = new CommonDiscount().applyDiscount(rareItemPrice);
        assertEquals(rareExpectedValue, rareItemDiscount);
    }

    @Test
    public void calculateTotalCommonDiscount() {
        BigDecimal zeroComicsTotal = new CommonDiscount().getDiscount(comicsZero);
        assertEquals(BigDecimal.ZERO, zeroComicsTotal);

        BigDecimal oneExpectedValue = new BigDecimal(0.9);
        oneExpectedValue = oneExpectedValue.setScale(1, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal oneComicsTotal = new CommonDiscount().getDiscount(comicsOne);
        assertEquals(oneExpectedValue, oneComicsTotal);

        BigDecimal twoExpectedValue = new BigDecimal(10.9);
        twoExpectedValue = twoExpectedValue.setScale(1, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal twoComicsTotal = new CommonDiscount().getDiscount(comicsTwo);
        assertEquals(twoExpectedValue, twoComicsTotal);
    }

}

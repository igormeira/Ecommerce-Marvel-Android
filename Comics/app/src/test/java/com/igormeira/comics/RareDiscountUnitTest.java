package com.igormeira.comics;

import com.igormeira.comics.model.Comic;
import com.igormeira.comics.model.discount.RareDiscount;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RareDiscountUnitTest {

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

    @Test
    public void calculateOneItemRareDiscount() {
        BigDecimal commonExpectedValue = new BigDecimal(0.75);
        BigDecimal commonItemPrice = commonComic.getPrice();
        BigDecimal commonItemDiscount = new RareDiscount().applyDiscount(commonItemPrice);
        assertEquals(commonExpectedValue, commonItemDiscount);

        BigDecimal rareExpectedValue = new BigDecimal(7.50);
        rareExpectedValue = rareExpectedValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal rareItemPrice = rareComic.getPrice();
        BigDecimal rareItemDiscount = new RareDiscount().applyDiscount(rareItemPrice);
        assertEquals(rareExpectedValue, rareItemDiscount);
    }

    @Test
    public void calculateTotalRareDiscount() {
        BigDecimal zeroComicsTotal = new RareDiscount().getDiscount(comicsZero);
        assertEquals(BigDecimal.ZERO, zeroComicsTotal);

        BigDecimal oneExpectedValue = new BigDecimal(0.75);
        BigDecimal oneComicsTotal = new RareDiscount().getDiscount(comicsOne);
        assertEquals(oneExpectedValue, oneComicsTotal);

        BigDecimal twoExpectedValue = new BigDecimal(8.25);
        BigDecimal twoComicsTotal = new RareDiscount().getDiscount(comicsTwo);
        assertEquals(twoExpectedValue, twoComicsTotal);
    }

}

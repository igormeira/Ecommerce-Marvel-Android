package com.igormeira.comics;

import com.igormeira.comics.model.Comic;
import com.igormeira.comics.model.discount.WithoutDiscount;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class WithoutDiscountUnitTest {

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
    public void calculateOneItemWithoutDiscount() {
        BigDecimal commonItemPrice = commonComic.getPrice();
        BigDecimal commonItemDiscount = new WithoutDiscount().applyDiscount(commonItemPrice);
        assertEquals(commonItemPrice, commonItemDiscount);

        BigDecimal rareItemPrice = rareComic.getPrice();
        BigDecimal rareItemDiscount = new WithoutDiscount().applyDiscount(rareItemPrice);
        assertEquals(rareItemPrice, rareItemDiscount);
    }

    @Test
    public void calculateTotalWithoutDiscount() {
        BigDecimal zeroComicsTotal = new WithoutDiscount().getDiscount(comicsZero);
        assertEquals(BigDecimal.ZERO, zeroComicsTotal);

        BigDecimal oneComicsTotal = new WithoutDiscount().getDiscount(comicsOne);
        assertEquals(BigDecimal.ONE, oneComicsTotal);

        BigDecimal twoComicsTotal = new WithoutDiscount().getDiscount(comicsTwo);
        BigDecimal expectedSum = BigDecimal.ONE.add(BigDecimal.TEN);
        assertEquals(expectedSum, twoComicsTotal);
    }

}

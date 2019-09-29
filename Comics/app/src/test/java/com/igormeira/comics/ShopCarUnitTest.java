package com.igormeira.comics;

import com.igormeira.comics.model.Comic;
import com.igormeira.comics.model.ShopCar;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ShopCarUnitTest {

    private Comic commonComic, rareComic;
    private List<Comic> comicsZero, comicsOne, comicsTwo;
    private ShopCar shopCar;

    @Before
    public void createShopCar() {
        commonComic = new Comic("Titulo", "Descricao", BigDecimal.TEN,
                "Thumbnail", "Comum");
        rareComic = new Comic("Titulo", "Descricao", BigDecimal.TEN,
                "Thumbnail", "Raro");

        comicsZero = new ArrayList<>();
        comicsOne = Collections.singletonList(commonComic);
        comicsTwo = Arrays.asList(commonComic, rareComic);

        shopCar = new ShopCar(comicsOne);
    }

    @Test
    public void getComicListFromShopCar() {
        Comic comic = shopCar.getComics().get(0);
        assertEquals(commonComic, comic);

        shopCar.setComics(comicsTwo);
        Comic comicIndexZero = shopCar.getComics().get(0);
        Comic comicIndexOne = shopCar.getComics().get(1);
        assertEquals(commonComic, comicIndexZero);
        assertEquals(rareComic, comicIndexOne);
    }

    @Test
    public void setComicListFromShopCar() {
        assertEquals(1, shopCar.getComics().size());

        shopCar.setComics(comicsTwo);
        assertEquals(2, shopCar.getComics().size());

        shopCar.setComics(comicsZero);
        assertEquals(0, shopCar.getComics().size());
    }

    @Test
    public void equalityBetweenShopCars() {
        ShopCar shopCarCopy = new ShopCar(comicsOne);

        ShopCar shopCarComicTwo = new ShopCar(comicsTwo);

        Boolean equalsResultCopy = shopCar.equals(shopCarCopy);
        assertEquals(true, equalsResultCopy);

        Boolean equalsResultTwo = shopCar.equals(shopCarComicTwo);
        assertEquals(false, equalsResultTwo);
    }
}

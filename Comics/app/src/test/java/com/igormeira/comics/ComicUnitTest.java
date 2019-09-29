package com.igormeira.comics;

import com.igormeira.comics.model.Comic;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import static org.junit.Assert.*;

public class ComicUnitTest {

    private Comic comic;

    @Before
    public  void createComic() {
        comic = new Comic("Titulo", "Descricao", BigDecimal.TEN,
                                "Thumbnail", "Comum");
    }

    @Test
    public void getTitleFromComic() {
        String title = comic.getTitle();
        assertEquals("Titulo", title);
    }

    @Test
    public void getDescriptionFromComic() {
        String description = comic.getDescription();
        assertEquals("Descricao", description);
    }

    @Test
    public void getPriceFromComic() {
        BigDecimal price = comic.getPrice();
        assertEquals(BigDecimal.TEN, price);
    }

    @Test
    public void getThumbnailFromComic() {
        String thumbnail = comic.getThumbnail();
        assertEquals("Thumbnail", thumbnail);
    }

    @Test
    public void getTypeFromComic() {
        String type = comic.getType();
        assertEquals("Comum", type);
    }

    @Test
    public void equalityBetweenComics() {
        Comic comicCopy = new Comic("Titulo", "Descricao", BigDecimal.TEN,
                "Thumbnail", "Comum");

        Comic comicTitleDiff = new Comic("TituloDiff", "Descricao", BigDecimal.TEN,
                "Thumbnail", "Comum");

        Comic comicDescriptionDiff = new Comic("Titulo", "DescricaoDiff", BigDecimal.TEN,
                "Thumbnail", "Comum");

        Comic comicPriceDiff = new Comic("Titulo", "Descricao", BigDecimal.ONE,
                "Thumbnail", "Comum");

        Comic comicThumbnailDiff = new Comic("Titulo", "Descricao", BigDecimal.TEN,
                "ThumbnailDiff", "Comum");

        Comic comicTypeDiff = new Comic("Titulo", "Descricao", BigDecimal.TEN,
                "Thumbnail", "Raro");

        Boolean equalsResultCopy = comic.equals(comicCopy);
        assertEquals(true, equalsResultCopy);

        Boolean equalsResultTitleDiff = comic.equals(comicTitleDiff);
        assertEquals(false, equalsResultTitleDiff);

        Boolean equalsResultDescDiff = comic.equals(comicDescriptionDiff);
        assertEquals(false, equalsResultDescDiff);

        Boolean equalsResultPriceDiff = comic.equals(comicPriceDiff);
        assertEquals(false, equalsResultPriceDiff);

        Boolean equalsResultThumbDiff = comic.equals(comicThumbnailDiff);
        assertEquals(false, equalsResultThumbDiff);

        Boolean equalsResultTypeDiff = comic.equals(comicTypeDiff);
        assertEquals(false, equalsResultTypeDiff);
    }
}

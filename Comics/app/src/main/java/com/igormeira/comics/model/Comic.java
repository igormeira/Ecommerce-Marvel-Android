package com.igormeira.comics.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe modelo para Comic.
 */
public class Comic implements Serializable {

    private final String title;
    private final String description;
    private final BigDecimal price;
    private final String thumbnail;
    private final String type;

    /**
     * Construtor para classe Comic.
     *
     * @param title
     * @param description
     * @param price
     * @param thumbnail
     * @param type
     */
    public Comic(String title, String description, BigDecimal price,
                 String thumbnail, String type) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
        this.type = type;
    }

    /**
     * Retorna o título (title).
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retorna o preço (price).
     *
     * @return BigDecimal
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Retorna a thumbnail.
     *
     * @return String
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Retorna a descrição (description).
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retorna o tipo (type).
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comic comic = (Comic) o;
        return title.equals(comic.title) &&
                description.equals(comic.description) &&
                price.equals(comic.price) &&
                thumbnail.equals(comic.thumbnail) &&
                type.equals(comic.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price, thumbnail, type);
    }
}

package com.igormeira.comics.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Comic implements Serializable {

    private final String title;
    private final String description;
    private final BigDecimal price;
    private final String thumbnail;
    private final String type;

    public Comic(String title, String description, BigDecimal price,
                 String thumbnail, String type) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

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

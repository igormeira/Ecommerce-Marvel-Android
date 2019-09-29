package com.igormeira.comics.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
}
